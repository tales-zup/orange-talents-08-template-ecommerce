package com.zup.ecommerce.usuario;

import com.zup.ecommerce.commons.validation.ValorUnico;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioRequest {

    @NotBlank
    @Email
    @ValorUnico(classe = Usuario.class, nomeDoCampo = "login", message = "Já existe um usuário com este email.")
    private String login;

    @NotBlank
    @Size(min = 6)
    private String senha;

    public UsuarioRequest(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }
}
