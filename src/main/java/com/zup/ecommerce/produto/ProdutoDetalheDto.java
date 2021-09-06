package com.zup.ecommerce.produto;

import com.zup.ecommerce.caracteristica.Caracteristica;
import com.zup.ecommerce.opiniao.OpiniaoDto;
import com.zup.ecommerce.pergunta.PerguntaDto;

import java.math.BigDecimal;
import java.util.List;

public class ProdutoDetalheDto {

    private List<String> links;
    private String nome;
    private BigDecimal preco;
    private List<Caracteristica> caracteristicas;
    private String descricao;
    private Double mediaDeNotas;
    private Integer totalDeNotas;
    private List<OpiniaoDto> opinioes;
    private List<PerguntaDto> perguntas;

    public ProdutoDetalheDto(List<String> links, String nome, BigDecimal preco, List<Caracteristica> caracteristicas,
                             String descricao, Double mediaDeNotas, Integer totalDeNotas, List<OpiniaoDto> opinioes,
                             List<PerguntaDto> perguntas) {
        this.links = links;
        this.nome = nome;
        this.preco = preco;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.mediaDeNotas = mediaDeNotas;
        this.totalDeNotas = totalDeNotas;
        this.opinioes = opinioes;
        this.perguntas = perguntas;
    }

    public List<String> getLinks() {
        return links;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public List<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getMediaDeNotas() {
        return mediaDeNotas;
    }

    public Integer getTotalDeNotas() {
        return totalDeNotas;
    }

    public List<OpiniaoDto> getOpinioes() {
        return opinioes;
    }

    public List<PerguntaDto> getPerguntas() {
        return perguntas;
    }
}
