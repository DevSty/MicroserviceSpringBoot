package pe.com.stavaray.alumnofeign.validar.curso;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("curso")
public interface CursoClient {

    @GetMapping(path = "api/curso/{alumnoId}")
    CursoResponse validarAlumno(@PathVariable("alumnoId") Long alumnoId);
}
