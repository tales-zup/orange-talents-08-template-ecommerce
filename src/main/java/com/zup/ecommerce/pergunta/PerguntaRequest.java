package com.zup.ecommerce.pergunta;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PerguntaRequest {

    @NotBlank
    private String titulo;

    @NotNull
    private Long idUsuario;

    @NotNull
    private Long idProduto;

    public String getTitulo() {
        return titulo;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public Long getIdProduto() {
        return idProduto;
    }
}
