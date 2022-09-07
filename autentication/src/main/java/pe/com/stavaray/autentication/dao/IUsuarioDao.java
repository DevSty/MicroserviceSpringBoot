package pe.com.stavaray.autentication.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.stavaray.autentication.model.Usuario;

import java.util.Optional;

public interface IUsuarioDao extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsuario(String usuario);
}
