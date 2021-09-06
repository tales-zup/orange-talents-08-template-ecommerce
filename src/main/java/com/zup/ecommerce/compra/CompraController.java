package com.zup.ecommerce.compra;

import com.zup.ecommerce.produto.Produto;
import com.zup.ecommerce.produto.ProdutoRepository;
import com.zup.ecommerce.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    public String cadastrarCompra(@RequestBody @Valid CompraRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario logado = (Usuario) authentication.getPrincipal();

        Produto produto = produtoRepository.findById(request.getIdProduto()).orElseThrow(
                () -> new IllegalArgumentException("Esse produto não existe."));

        if(produto.getQuantidade() < 1)
            throw new IllegalArgumentException("O estoque do produto " + produto.getNome() + " está zerado.");

        Compra compra = request.toModel(produto, logado);
        produto.diminuirUmaUnidadeNoEstoque();
        produtoRepository.save(produto);
        compraRepository.save(compra);

        return "Compra cadastrada!";
    }

}
