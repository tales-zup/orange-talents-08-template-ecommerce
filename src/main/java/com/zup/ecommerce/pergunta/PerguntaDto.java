package com.zup.ecommerce.pergunta;

import java.time.LocalDateTime;

public class PerguntaDto {

    private Long id;
    private String titulo;
    private Long idUsuario;
    private Long idProduto;
    private LocalDateTime dataCadastro;

    public PerguntaDto(Long id, String titulo, Long idUsuario, Long idProduto, LocalDateTime dataCadastro) {
        this.id = id;
        this.titulo = titulo;
        this.idUsuario = idUsuario;
        this.idProduto = idProduto;
        this.dataCadastro = dataCadastro;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }
}
