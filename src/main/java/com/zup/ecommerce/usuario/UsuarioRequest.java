package com.zup.ecommerce.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UsuarioRequest {

    @NotEmpty
    @Email
    private String login;

    @NotEmpty
    @Size(min = 6)
    private String senha;

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }
}
