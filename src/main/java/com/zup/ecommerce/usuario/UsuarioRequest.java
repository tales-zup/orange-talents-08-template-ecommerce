package com.zup.ecommerce.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class UsuarioRequest {

    @NotEmpty
    @Email
    private String login;

    @NotEmpty
    @Min(value = 6)
    private String senha;

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }
}
