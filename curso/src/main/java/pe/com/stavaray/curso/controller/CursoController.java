package pe.com.stavaray.curso.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.stavaray.alumnofeign.validar.curso.CursoResponse;
import pe.com.stavaray.curso.service.ICursoService;

@Slf4j
@RestController
@RequestMapping("api/curso")
@AllArgsConstructor
public class CursoController {

    private final ICursoService cursoService;

    @GetMapping(path = "{alumnoId}")
    public CursoResponse validarAlumno(@PathVariable("alumnoId") Long alumnoId){
        boolean estaInavilitado = cursoService.estaInavilitado(alumnoId);
        log.info("Validacion para cliente: {}",alumnoId);
        return new CursoResponse(estaInavilitado);
    }

}
