package com.zup.ecommerce.produto;

import com.zup.ecommerce.caracteristica.Caracteristica;
import com.zup.ecommerce.categoria.Categoria;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ProdutoDto {

    private Long id;
    private String nome;
    private BigDecimal valor;
    private Integer quantidade;
    private String descricao;
    private Categoria categoria;
    private List<Caracteristica> caracteristicas;
    private LocalDateTime dataCadastro;

    public ProdutoDto(Long id, String nome, BigDecimal valor, Integer quantidade, String descricao,
                      Categoria categoria, List<Caracteristica> caracteristicas, LocalDateTime dataCadastro) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoria = categoria;
        this.caracteristicas = caracteristicas;
        this.dataCadastro = dataCadastro;
    }

    public Long getId() {
        return id;
    }

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

    public Categoria getCategoria() {
        return categoria;
    }

    public List<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }
}
