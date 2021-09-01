package com.zup.ecommerce.config.security;

import com.zup.ecommerce.commons.HashService;
import com.zup.ecommerce.usuario.login.LoginRequest;
import com.zup.ecommerce.usuario.login.TokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private HashService hashService;

    @PostMapping
    public ResponseEntity<?> autenticar(@RequestBody @Valid LoginRequest request) {
        UsernamePasswordAuthenticationToken login = request.converter();
        request.setSenha(hashService.hash(request.getSenha()));
        try {
            Authentication authentication = authenticationManager.authenticate(login);
            String token = tokenService.gerarToken(authentication);
            return ResponseEntity.ok(new TokenDto(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
