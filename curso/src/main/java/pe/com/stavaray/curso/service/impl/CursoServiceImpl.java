package pe.com.stavaray.curso.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.stavaray.curso.dao.ICursoDao;
import pe.com.stavaray.curso.model.Curso;
import pe.com.stavaray.curso.service.ICursoService;

import java.util.Date;

@Service
@AllArgsConstructor
public class CursoServiceImpl implements ICursoService {

    private final ICursoDao cursoDao;

    @Override
    public boolean estaInavilitado(Long id) {
        cursoDao.save(
                Curso.builder()
                        .alumnoId(id)
                        .curso("BootCamp Spring Boot & QA")
                        .inavilitado(false)
                        .fechaCreacion(new Date())
                        .build()
        );
        return false;
    }
}
