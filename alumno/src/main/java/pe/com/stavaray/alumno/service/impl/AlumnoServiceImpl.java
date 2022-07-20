package pe.com.stavaray.alumno.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.com.stavaray.alumno.dao.IAlumnoDao;
import pe.com.stavaray.alumno.model.Alumno;
import pe.com.stavaray.alumno.rabbitmq.RabbitMQMessageProducer;
import pe.com.stavaray.alumno.service.IAlumnoService;
import pe.com.stavaray.alumnofeign.notificacion.NotificacionRequest;
import pe.com.stavaray.alumnofeign.validar.curso.CursoClient;
import pe.com.stavaray.alumnofeign.validar.curso.CursoResponse;

import java.util.List;
import java.util.Optional;

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

        Alumno alumnoResponse = alumnoDao.save(alumno);
        CursoResponse  cursoResponse= cursoClient.validarAlumno(alumnoResponse.getId());

        if(cursoResponse.inavilitado()){
            throw new IllegalStateException("El alumno esta inavilitado del curso");
        }
        NotificacionRequest notificacionRequest = new NotificacionRequest(
                alumno.getId(),
                alumno.getEmail(),
                String.format("Hola %s, bienvenido a NTTData..."
                        ,alumno.getNombre())
        );

        rabbitMQMessageProducer.publish(notificacionRequest
                ,"internal.exchange"
                ,"internal.notification.routing-key");

        return alumnoResponse;
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
