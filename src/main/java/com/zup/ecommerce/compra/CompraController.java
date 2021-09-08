package com.zup.ecommerce.compra;

import com.zup.ecommerce.commons.utils.email.Email;
import com.zup.ecommerce.commons.utils.email.EmailService;
import com.zup.ecommerce.produto.Produto;
import com.zup.ecommerce.produto.ProdutoRepository;
import com.zup.ecommerce.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity cadastrarCompra(@RequestBody @Valid CompraRequest request,
                                     HttpServletResponse response) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario logado = (Usuario) authentication.getPrincipal();

        Produto produto = produtoRepository.findById(request.getIdProduto()).orElseThrow(
                () -> new IllegalArgumentException("Esse produto não existe."));

        if(produto.getQuantidade() < 1)
            throw new IllegalArgumentException("O estoque do produto " + produto.getNome() + " está zerado.");

        Compra compra = request.toModel(produto, logado);
        produto.diminuirUmaUnidadeNoEstoque();
        produtoRepository.save(produto);
        compra = compraRepository.save(compra);

        Email email = emailService.construir(compra);
        emailService.enviar(email);

        String url = compra.getGateway().getUrl().replace("{idGeradoDaCompra}", compra.getId().toString())
                        .replace("{urlRetornoAppPosPagamento}", "https://mercadolivre.com");

        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(url)).build();

    }

}
