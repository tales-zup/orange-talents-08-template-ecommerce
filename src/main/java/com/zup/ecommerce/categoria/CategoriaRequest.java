package com.zup.ecommerce.categoria;

import com.zup.ecommerce.commons.validation.ExistsId;
import com.zup.ecommerce.commons.validation.ValorUnico;

import javax.validation.constraints.NotEmpty;

public class CategoriaRequest {

    @NotEmpty
    @ValorUnico(classe = Categoria.class, nomeDoCampo = "nome", mensagem = "Já existe uma categoria com esse nome.")
    private String nome;

    @ExistsId(classe = Categoria.class, nomeDoCampo = "id", opcional = true)
    private Long idCategoriaMae;

    public CategoriaRequest(String nome, Long idCategoriaMae) {
        this.nome = nome;
        this.idCategoriaMae = idCategoriaMae;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdCategoriaMae() {
        return idCategoriaMae;
    }
}
