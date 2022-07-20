package pe.com.stavaray.notificacion.service;

import pe.com.stavaray.alumnofeign.notificacion.NotificacionRequest;

public interface INotificacionService {

    public boolean enviarNotificacion(NotificacionRequest notificacionRequest);

}
