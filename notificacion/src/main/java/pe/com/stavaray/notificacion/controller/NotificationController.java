package pe.com.stavaray.notificacion.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.stavaray.alumnofeign.notificacion.NotificacionRequest;
import pe.com.stavaray.notificacion.service.INotificacionService;

@RestController
@RequestMapping("api/notificacion")
@AllArgsConstructor
@Slf4j
public class NotificationController {

    private final INotificacionService notificacionService;

    @PostMapping
    public void enviarNotificacion(@RequestBody NotificacionRequest notificacionRequest){
        log.info("Nueva notificacion .. {}",notificacionRequest);
        notificacionService.enviarNotificacion(notificacionRequest);
    }

}
