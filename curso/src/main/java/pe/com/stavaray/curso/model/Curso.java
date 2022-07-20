package pe.com.stavaray.curso.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long alumnoId;
    private String curso;
    private boolean inavilitado;
    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
}
