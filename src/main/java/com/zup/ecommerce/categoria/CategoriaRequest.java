package com.zup.ecommerce.categoria;

import com.zup.ecommerce.commons.validation.ExistsId;
import com.zup.ecommerce.commons.validation.ValorUnico;

import javax.validation.constraints.NotEmpty;
import java.util.Optional;

public class CategoriaRequest {

    @NotEmpty
    @ValorUnico(classe = Categoria.class, nomeDoCampo = "nome", message = "Já existe uma categoria com esse nome.")
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

    public Categoria toModel(CategoriaRepository repository) {
        if(this.idCategoriaMae == null)
            return new Categoria(this.nome, null);

        Optional<Categoria> mae = repository.findById(this.idCategoriaMae);
        if(mae.isPresent())
            return new Categoria(this.nome, mae.get());
        return new Categoria(this.nome, null);
    }
}
