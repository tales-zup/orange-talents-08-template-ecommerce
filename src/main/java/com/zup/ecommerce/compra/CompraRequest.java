package com.zup.ecommerce.compra;

import com.zup.ecommerce.commons.validation.ExistsId;
import com.zup.ecommerce.produto.Produto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CompraRequest {

    @NotNull
    @Positive
    private Integer quantidade;

    @NotNull
    @ExistsId(classe = Produto.class, nomeDoCampo = "id", message = "Esse produto não existe.")
    private Long idProduto;

    @NotNull
    private GatewayPagamento gatewayPagamento;

}
