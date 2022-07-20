package pe.com.stavaray.notificacion.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Notificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificacionId;
    private Long alumnoId;
    private String alumnoEmail;
    private String remitente;
    private String mensaje;
    @Temporal(TemporalType.DATE)
    private Date horaEnvio;
}

