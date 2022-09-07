package pe.com.stavaray.autentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.stavaray.autentication.dto.NuevoUsuarioDto;
import pe.com.stavaray.autentication.dto.RequestDto;
import pe.com.stavaray.autentication.dto.TokenDto;
import pe.com.stavaray.autentication.dto.UsuarioDto;
import pe.com.stavaray.autentication.model.Usuario;
import pe.com.stavaray.autentication.service.UsuarioService;

@RestController
@RequestMapping("api/autentication")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody UsuarioDto dto){
        TokenDto tokenDto = usuarioService.login(dto);
        if(tokenDto == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/validar")
    public ResponseEntity<TokenDto> validate(@RequestParam String token, @RequestBody RequestDto dto){
        TokenDto tokenDto = usuarioService.validate(token, dto);
        if(tokenDto == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/crear")
    public ResponseEntity<Usuario> create(@RequestBody NuevoUsuarioDto dto){
        Usuario usuario = usuarioService.save(dto);
        if(usuario == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(usuario);
    }

}
