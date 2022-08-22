package pe.com.stavaray.alumno.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.stavaray.alumno.model.Alumno;
import pe.com.stavaray.alumno.service.IAlumnoService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/alumno")
@AllArgsConstructor
public class AlumnoController {

    private final IAlumnoService alumnoService;

    @GetMapping
    public ResponseEntity<?> listarAlumnos() {
        List<Alumno> alumnos = alumnoService.findAll();
        log.info("Listando Alumnos {}",alumnos.stream().toList());
        return new ResponseEntity(alumnos, alumnos.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> obtenerAlumno(@PathVariable Long id) {
        log.info("Obtener Alumno: "+ id);
        Alumno alumno = alumnoService.findById(id);
        return new ResponseEntity<>(alumno,alumno!=null?HttpStatus.OK:HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> registrarAlumno(@Valid @RequestBody Alumno alumno) {
        log.info("Nuevo registro de alumno {}", alumno);
        Alumno newAlumno = alumnoService.save(alumno);
        String resultado = alumnoService.validarAlumno(newAlumno);
        log.info("Resultado: {}",resultado);
        if (resultado.equals("OK")){
            alumnoService.registrarNotificacion(newAlumno);
            return new ResponseEntity<Alumno>(alumnoService.save(alumno), HttpStatus.OK);
        }
        return new ResponseEntity("Servicio validarAlumno no disponible", HttpStatus.OK);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> modificarAlumno(@Valid @RequestBody Alumno alumno, @PathVariable Long id) {
        Alumno alumnofind = alumnoService.findById(id);
        alumnofind.setNombre(alumno.getNombre());
        alumnofind.setApellidoPaterno(alumno.getApellidoPaterno());
        alumnofind.setApellidoMaterno(alumno.getApellidoMaterno());
        alumnofind.setEmail(alumno.getEmail());
        alumnofind.setFechaRegistro(alumno.getFechaRegistro());
        log.info("Modificando datos de alumno {}", alumnofind);
        return new ResponseEntity<Alumno>(alumnoService.modifyAlumno(alumnofind), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> eliminarAlumno(@PathVariable Long id) {
        log.info("Eliminando alumno: "+ id);
        boolean var = alumnoService.deleteById(id);
        return new ResponseEntity<>(var?HttpStatus.OK:HttpStatus.NOT_FOUND);
    }

}
