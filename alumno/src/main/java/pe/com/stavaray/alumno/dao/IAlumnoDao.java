package pe.com.stavaray.alumno.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.stavaray.alumno.model.Alumno;

public interface IAlumnoDao extends JpaRepository<Alumno, Long> {
}
