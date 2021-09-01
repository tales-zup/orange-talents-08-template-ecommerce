package com.zup.ecommerce.categoria;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nome;

    @ManyToOne
    private Categoria categoriaMae;

    public Categoria() {
    }

    public Categoria(String nome, Categoria categoriaMae) {
        this.nome = nome;
        this.categoriaMae = categoriaMae;
    }
}
