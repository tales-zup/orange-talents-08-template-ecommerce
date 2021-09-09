package com.zup.ecommerce.apiexterna;

import javax.validation.constraints.NotNull;

public class SistemaRankingRequest {

    @NotNull
    private Long idCompra;
    @NotNull
    private Long idVendedor;

    public SistemaRankingRequest(@NotNull Long idCompra, @NotNull Long idVendedor) {
        this.idCompra = idCompra;
        this.idVendedor = idVendedor;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public Long getIdVendedor() {
        return idVendedor;
    }

    @Override
    public String toString() {
        return "FormSistemaRanking{" +
                "idCompra=" + idCompra +
                ", idVendedor=" + idVendedor +
                '}';
    }

}
