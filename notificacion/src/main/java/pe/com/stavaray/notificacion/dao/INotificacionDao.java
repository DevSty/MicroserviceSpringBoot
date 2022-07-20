package pe.com.stavaray.notificacion.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.stavaray.notificacion.model.Notificacion;

public interface INotificacionDao extends JpaRepository<Notificacion, Long> {
}
