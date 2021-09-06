package com.zup.ecommerce.compra;

import com.zup.ecommerce.commons.validation.ExistsId;
import com.zup.ecommerce.produto.Produto;
import com.zup.ecommerce.usuario.Usuario;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class CompraRequest {

    @NotNull
    @Positive
    private Integer quantidade;

    @NotNull
    @ExistsId(classe = Produto.class, nomeDoCampo = "id", message = "Esse produto não existe.")
    private Long idProduto;

    @NotNull
    private GatewayPagamento gatewayPagamento;

    public Compra toModel(Produto produto, Usuario usuario) {
        BigDecimal valor = produto.getValor().multiply(new BigDecimal(quantidade));
        return new Compra(gatewayPagamento, produto, quantidade, usuario, valor);
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public GatewayPagamento getGatewayPagamento() {
        return gatewayPagamento;
    }
}
