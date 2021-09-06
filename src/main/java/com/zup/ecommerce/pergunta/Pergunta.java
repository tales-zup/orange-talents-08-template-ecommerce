package com.zup.ecommerce.pergunta;

import com.zup.ecommerce.produto.Produto;
import com.zup.ecommerce.usuario.Usuario;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    @NotNull
    private LocalDateTime dataCadastro;

    @NotNull
    private Usuario usuario;

    @NotNull
    private Produto produto;

    public Pergunta(String titulo, LocalDateTime dataCadastro, Usuario usuario, Produto produto) {
        this.titulo = titulo;
        this.dataCadastro = dataCadastro;
        this.usuario = usuario;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Produto getProduto() {
        return produto;
    }
}
