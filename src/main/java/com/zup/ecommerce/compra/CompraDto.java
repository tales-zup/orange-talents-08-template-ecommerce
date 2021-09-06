package com.zup.ecommerce.compra;

import java.math.BigDecimal;

public class CompraDto {

    private Long id;
    private GatewayPagamento gateway;
    private Long idProduto;
    private Integer quantidade;
    private Long idUsuario;
    private BigDecimal valor;
    private StatusCompra status;

    public CompraDto(Long id, GatewayPagamento gateway, Long idProduto, Integer quantidade, Long idUsuario,
                     BigDecimal valor, StatusCompra status) {
        this.id = id;
        this.gateway = gateway;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.idUsuario = idUsuario;
        this.valor = valor;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public GatewayPagamento getGateway() {
        return gateway;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public StatusCompra getStatus() {
        return status;
    }
}
