package pe.com.stavaray.alumno.controller;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public record AlumnoRequest(Long id, String nombre, String apellidoPaterno,
                            String apellidoMaterno, String email, @Temporal(TemporalType.DATE) Date fechaRegistro ) {
}
