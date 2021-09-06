package com.zup.ecommerce.compra;

import com.zup.ecommerce.produto.Produto;
import com.zup.ecommerce.usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private GatewayPagamento gateway;

    @ManyToOne
    private Produto produto;

    @NotNull
    @Positive
    private Integer quantidade;

    @NotNull
    @ManyToOne
    private Usuario usuario;

    @NotNull
    private BigDecimal valor;

    @NotNull
    private StatusCompra status;

    public Compra(GatewayPagamento gateway, Produto produto, Integer quantidade, Usuario usuario, BigDecimal valor) {
        this.gateway = gateway;
        this.produto = produto;
        this.quantidade = quantidade;
        this.usuario = usuario;
        this.valor = valor;
        this.status = StatusCompra.INICIADA;
    }
}
