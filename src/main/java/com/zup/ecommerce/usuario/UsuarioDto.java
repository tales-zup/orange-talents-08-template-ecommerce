package com.zup.ecommerce.usuario;

import java.time.LocalDate;

public class UsuarioDto {

    private String login;

    private String senha;

    private LocalDate dataCadastro;

    public UsuarioDto(String login, String senha, LocalDate dataCadastro) {
        this.login = login;
        this.senha = senha;
        this.dataCadastro = dataCadastro;
    }
}
