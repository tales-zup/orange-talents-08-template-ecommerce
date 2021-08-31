package com.zup.ecommerce.usuario;

import com.zup.ecommerce.commons.HashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private HashService hashService;

    @PostMapping
    public UsuarioDto cadastrarUsuario(@RequestBody @Valid UsuarioRequest request) {
        String senha = hashService.hash(request.getSenha());
        Usuario usuario = new Usuario(request.getLogin(), senha);
        return new UsuarioDto(usuario.getLogin(), usuario.getSenha(), usuario.getDataCadastro());
    }

}
