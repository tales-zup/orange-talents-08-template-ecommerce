package com.zup.ecommerce.apiexterna;

import javax.validation.constraints.NotNull;

public class SistemaNotasRequest {

    @NotNull
    private Long idCompra;
    @NotNull
    private Long idUsuarioDaCompra;

    public SistemaNotasRequest(Long idCompra, Long idUsuarioDaCompra) {
        this.idCompra = idCompra;
        this.idUsuarioDaCompra = idUsuarioDaCompra;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public Long getIdUsuarioDaCompra() {
        return idUsuarioDaCompra;
    }

    @Override
    public String toString() {
        return "SistemaNotasRequest{" +
                "idCompra=" + idCompra +
                ", idUsuarioDaCompra=" + idUsuarioDaCompra +
                '}';
    }
}
