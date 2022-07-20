package pe.com.stavaray.alumnofeign.notificacion;

public record NotificacionRequest (Long alumnoId, String alumnoEmail, String mensaje){
}
