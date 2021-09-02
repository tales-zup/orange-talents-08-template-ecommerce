package com.zup.ecommerce.caracteristica;

import com.zup.ecommerce.produto.Produto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Caracteristica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    @NotNull
    @ManyToOne
    private Produto produto;

    public Caracteristica() {
    }

    public Caracteristica(String nome, String descricao, Produto produto) {
        this.nome = nome;
        this.descricao = descricao;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
