package com.zup.ecommerce.produto;


import com.zup.ecommerce.caracteristica.Caracteristica;
import com.zup.ecommerce.caracteristica.CaracteristicaRequest;
import com.zup.ecommerce.categoria.Categoria;
import com.zup.ecommerce.categoria.CategoriaRepository;
import com.zup.ecommerce.commons.validation.ExistsId;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @Size(max = 1000)
    private String descricao;

    @NotNull
    @ExistsId(classe = Categoria.class, nomeDoCampo = "id", message = "Essa categoria não existe.")
    private Long idCategoria;

    @NotNull
    @Size(min = 3, message = "A quantidade miníma de caracteristicas é 3.")
    private List<CaracteristicaRequest> caracteristicas;

    public Produto toModel(CategoriaRepository categoriaRepository) {
        Categoria categoria = categoriaRepository.findById(idCategoria).orElseThrow();
        return new Produto(nome, valor, quantidade, descricao, categoria);
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

    public Long getIdCategoria() {
        return idCategoria;
    }

    public List<CaracteristicaRequest> getCaracteristicas() {
        return caracteristicas;
    }
}
