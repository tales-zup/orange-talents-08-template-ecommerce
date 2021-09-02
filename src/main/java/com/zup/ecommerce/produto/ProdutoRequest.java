package com.zup.ecommerce.produto;


import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProdutoRequest {

    @NotBlank
    private String nome;

    @NotNull
    @DecimalMin(value = "0.1")
    private BigDecimal valor;

    @NotNull
    @Min(value = 0)
    private Integer quantidade;

    @NotBlank
    private String descricao;

    @NotNull
    private Long idCategoria;

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }
}
