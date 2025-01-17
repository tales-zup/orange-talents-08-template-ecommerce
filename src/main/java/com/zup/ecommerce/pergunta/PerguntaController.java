package com.zup.ecommerce.pergunta;

import com.zup.ecommerce.commons.utils.email.Email;
import com.zup.ecommerce.commons.utils.email.EmailService;
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
@RequestMapping("/perguntas")
public class PerguntaController {

    @Autowired
    private PerguntaRepository perguntaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EmailService emailService;

    @PostMapping
    public PerguntaDto cadastrarPergunta(@RequestBody @Valid PerguntaRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario logado = (Usuario) authentication.getPrincipal();
        Pergunta pergunta = request.toModel(produtoRepository, logado);
        pergunta = perguntaRepository.save(pergunta);
        Email email = emailService.construir(pergunta);
        emailService.enviar(email);
        return new PerguntaDto(pergunta.getId(), pergunta.getTitulo(), pergunta.getUsuario().getId(),
                pergunta.getProduto().getId(), pergunta.getDataCadastro());
    }

}
