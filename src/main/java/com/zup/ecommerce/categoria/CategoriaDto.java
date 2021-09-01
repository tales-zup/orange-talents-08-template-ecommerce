package com.zup.ecommerce.categoria;

public class CategoriaDto {

    private Long id;

    private String nome;

    private Categoria categoriaMae;

    public CategoriaDto(Long id, String nome, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.categoriaMae = categoria;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Categoria getCategoriaMae() {
        return categoriaMae;
    }
}
