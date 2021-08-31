package com.zup.ecommerce.usuario;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Email
    private String login;

    @NotEmpty
    @Size(min = 6)
    private String senha;

    @NotNull
    private LocalDateTime dataCadastro;

    public Usuario() {
        this.dataCadastro = LocalDateTime.now();
    }

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
        this.dataCadastro = LocalDateTime.now();
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }
}
