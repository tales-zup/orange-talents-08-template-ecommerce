package com.zup.ecommerce.produto;

import com.zup.ecommerce.caracteristica.CaracteristicaRequest;
import com.zup.ecommerce.categoria.Categoria;
import com.zup.ecommerce.categoria.CategoriaRepository;
import com.zup.ecommerce.commons.validation.ExistsId;
import com.zup.ecommerce.usuario.Usuario;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

public class ProdutoRequest {

    @NotBlank
    private String nome;

    @NotNull
    @Positive
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
    @Valid
    private List<CaracteristicaRequest> caracteristicas;

    public Produto toModel(CategoriaRepository categoriaRepository, Usuario usuario) {
        Categoria categoria = categoriaRepository.findById(idCategoria).orElseThrow();
        return new Produto(nome, valor, quantidade, descricao, categoria, usuario);
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
