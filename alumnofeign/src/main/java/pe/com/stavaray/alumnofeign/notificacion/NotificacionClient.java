package pe.com.stavaray.alumnofeign.notificacion;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name="notificacion",
        url = "${alumnofeign.notificacion.url}"
)
public interface NotificacionClient {
    @PostMapping(path = "api/notificacion")
    void enviarNotificacion(@RequestBody NotificacionRequest notificacionRequest);
}