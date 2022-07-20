package pe.com.stavaray.notificacion.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.stavaray.alumnofeign.notificacion.NotificacionRequest;
import pe.com.stavaray.notificacion.dao.INotificacionDao;
import pe.com.stavaray.notificacion.model.Notificacion;
import pe.com.stavaray.notificacion.service.INotificacionService;

import java.util.Date;

@Service
@AllArgsConstructor
public class NotificacionServiceImpl implements INotificacionService {

    private final INotificacionDao notificacionDao;

    @Override
    public boolean enviarNotificacion(NotificacionRequest notificacionRequest) {
        notificacionDao.save(
                Notificacion.builder()
                        .alumnoId(notificacionRequest.alumnoId())
                        .alumnoEmail(notificacionRequest.alumnoEmail())
                        .remitente("NTTData")
                        .mensaje(notificacionRequest.mensaje())
                        .horaEnvio(new Date())
                        .build()
        );
        return false;
    }
}
