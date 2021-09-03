package com.zup.ecommerce.opiniao;

import com.zup.ecommerce.commons.validation.ExistsId;
import com.zup.ecommerce.produto.Produto;
import com.zup.ecommerce.produto.ProdutoRepository;
import com.zup.ecommerce.usuario.Usuario;

import javax.persistence.ManyToOne;
import javax.validation.constraints.*;

public class OpiniaoRequest {

    @NotBlank
    private String titulo;

    @Min(1) @Max(5)
    private Integer nota;

    @NotBlank
    @Size(max = 500)
    private String descricao;

    @NotNull
    @ManyToOne
    @ExistsId(classe = Produto.class, nomeDoCampo = "id", message = "Esse produto não existe.")
    private Long idProduto;

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public Opiniao toModel(ProdutoRepository repository, Usuario usuario) {
        Produto produto = repository.findById(idProduto).orElseThrow();
        return new Opiniao(titulo, descricao, produto, usuario);
    }
}
