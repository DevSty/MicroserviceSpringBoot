package pe.com.stavaray.alumno.service.impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.com.stavaray.alumno.dao.IAlumnoDao;
import pe.com.stavaray.alumno.model.Alumno;
import pe.com.stavaray.alumnoqueues.rabbitmq.RabbitMQMessageProducer;
import pe.com.stavaray.alumno.service.IAlumnoService;
import pe.com.stavaray.alumnofeign.notificacion.NotificacionRequest;
import pe.com.stavaray.alumnofeign.validar.curso.CursoClient;
import pe.com.stavaray.alumnofeign.validar.curso.CursoResponse;


import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class AlumnoServiceImpl implements IAlumnoService {

    private final IAlumnoDao alumnoDao;
    private final CursoClient cursoClient;

    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    @Override
    @Transactional(readOnly = true)
    public List<Alumno> findAll() {
        return alumnoDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Alumno findById(Long id) {
        return alumnoDao.findById(id).orElse(null);
    }

    @Override
    public Alumno save(Alumno alumno) {

        return  alumnoDao.save(alumno);

    }
    @CircuitBreaker(name = "validarAlumnoCB", fallbackMethod = "fallValidarAlumnoCB")
    @Retry(name = "validarAlumnoRetry")
    public String validarAlumno(Alumno alumno) {
        log.info("Estoy en validar alumno");
        CursoResponse cursoResponse = cursoClient.validarAlumno(alumno.getId());

        if(cursoResponse.inavilitado()){
            throw new IllegalStateException("El alumno esta inavilitado del curso");
        }
        return "OK";
    }
    public String fallValidarAlumnoCB(Alumno alumno, Exception e){
        return "NO_OK";
    }

    public void registrarNotificacion(Alumno alumno) {
        NotificacionRequest notificacionRequest = new NotificacionRequest(
                alumno.getId(),
                alumno.getEmail(),
                String.format("Hola %s, bienvenido a NTTData..."
                        ,alumno.getNombre())
        );

        rabbitMQMessageProducer.publish(notificacionRequest
                ,"internal.exchange"
                ,"internal.notification.routing-key");
    }

    @Override
    public Alumno modifyAlumno(Alumno alumno) {
        return alumnoDao.save(alumno);
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<Alumno> alu = alumnoDao.findById(id);
        if(alu.isPresent()){
            alumnoDao.deleteById(id);
            return true;
        }
        return false;
    }
}
