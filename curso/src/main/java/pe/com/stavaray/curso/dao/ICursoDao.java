package pe.com.stavaray.curso.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.stavaray.curso.model.Curso;

public interface ICursoDao extends JpaRepository<Curso,Long> {
}
