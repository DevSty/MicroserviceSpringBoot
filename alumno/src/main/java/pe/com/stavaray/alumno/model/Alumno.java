package pe.com.stavaray.alumno.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "alumnos")
public class Alumno{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "{NotEmpty.alumno.nombre}")
    @Pattern(regexp="[a-zA-Z]{2,20}", message = "{Pattern.alumno.nombre}")
    String nombre;
    @Column(name = "apellido_paterno")
    @NotEmpty (message = "{NotEmpty.alumno.apellidoPaterno}")
    @Pattern(regexp="[a-zA-Z]{2,20}", message = "{Pattern.alumno.apellidoPaterno}")
    String apellidoPaterno;
    @Column(name = "apellido_materno")
    @NotEmpty (message = "{NotEmpty.alumno.apellidoMaterno}")
    String apellidoMaterno;
    @NotEmpty (message = "{NotEmpty.alumno.email}")
    @Email(message = "{Email.alumno.email}")
    String email;
    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;

}
