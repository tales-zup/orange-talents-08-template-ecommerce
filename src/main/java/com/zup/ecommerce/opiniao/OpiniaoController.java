package com.zup.ecommerce.opiniao;

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

@RestController
@RequestMapping("/opinioes")
public class OpiniaoController {

    @Autowired
    private OpiniaoRepository opiniaoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    public OpiniaoDto cadastrarOpiniao(@RequestBody @Valid OpiniaoRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario logado = (Usuario) authentication.getPrincipal();
        Opiniao opiniao = request.toModel(produtoRepository, logado);

        opiniaoRepository.save(opiniao);

        return new OpiniaoDto(opiniao.getId(), opiniao.getTitulo(), opiniao.getNota(), opiniao.getDescricao(),
                opiniao.getProduto().getId(), opiniao.getUsuario().getId());
    }

}
