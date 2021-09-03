package com.zup.ecommerce.opiniao;

public class OpiniaoDto {

    private Long id;
    private String titulo;
    private String descricao;
    private Long idProduto;
    private Long idUsuario;

    public OpiniaoDto(Long id, String titulo, String descricao, Long idProduto, Long idUsuario) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.idProduto = idProduto;
        this.idUsuario = idUsuario;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }
}
