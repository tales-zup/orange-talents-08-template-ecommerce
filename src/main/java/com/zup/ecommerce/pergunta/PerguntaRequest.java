package com.zup.ecommerce.pergunta;

import com.zup.ecommerce.commons.validation.ExistsId;
import com.zup.ecommerce.produto.Produto;
import com.zup.ecommerce.produto.ProdutoRepository;
import com.zup.ecommerce.usuario.Usuario;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PerguntaRequest {

    @NotBlank
    private String titulo;

    @NotNull
    @ExistsId(classe = Produto.class, nomeDoCampo = "id", message = "Esse produto não existe.")
    private Long idProduto;

    public String getTitulo() {
        return titulo;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public Pergunta toModel(ProdutoRepository produtoRepository, Usuario usuario) {
        Produto produto = produtoRepository.findById(this.idProduto).orElseThrow(
                () -> new IllegalArgumentException("Esse produto não existe."));
        return new Pergunta(titulo, usuario, produto);
    }
}
