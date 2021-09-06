package com.zup.ecommerce.produto;

import com.zup.ecommerce.caracteristica.Caracteristica;
import com.zup.ecommerce.caracteristica.CaracteristicaRepository;
import com.zup.ecommerce.caracteristica.CaracteristicaRequest;
import com.zup.ecommerce.categoria.CategoriaRepository;
import com.zup.ecommerce.commons.ImageUploaderService;
import com.zup.ecommerce.opiniao.OpiniaoDto;
import com.zup.ecommerce.opiniao.OpiniaoRepository;
import com.zup.ecommerce.pergunta.PerguntaDto;
import com.zup.ecommerce.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CaracteristicaRepository caracteristicaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ImageUploaderService imageUploaderService;

    @Autowired
    private ImagemProdutoRepository imagemProdutoRepository;

    @Autowired
    private OpiniaoRepository opiniaoRepository;

    @PostMapping
    @Transactional
    public ProdutoDto cadastrarProduto(@RequestBody @Valid ProdutoRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario logado = (Usuario) authentication.getPrincipal();
        Produto produto = request.toModel(categoriaRepository, logado);
        List<Caracteristica> caracteristicas = new ArrayList<>();

        produto = produtoRepository.save(produto);
        for(CaracteristicaRequest cr : request.getCaracteristicas()) {
            Caracteristica caracteristica = new Caracteristica(cr.getNome(), cr.getDescricao(), produto);
            caracteristica = caracteristicaRepository.save(caracteristica);
            caracteristicas.add(caracteristica);
        }

        return new ProdutoDto(produto.getId(), produto.getNome(), produto.getValor(), produto.getQuantidade(),
                produto.getDescricao(), produto.getCategoria(), caracteristicas, produto.getDataCadastro());
    }

    @PostMapping("/{id}/imagens")
    @Transactional
    public String cadastrarImagemDeProduto(@PathVariable("id") Long id, @Valid NovasImagensRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario logado = (Usuario) authentication.getPrincipal();
        Set<String> links = imageUploaderService.envia(request.getImagens());
        Produto produto = produtoRepository.findById(id).orElseThrow();

        if(!produto.pertenceAoUsuario(logado))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        produto.associaImagens(links);
        produtoRepository.save(produto);

        return produto.toString();
    }

    @GetMapping("/{id}/detalhar")
    public ProdutoDetalheDto detalharProduto(@PathVariable("id") Long id) {
        Produto produto = produtoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Esse produto não existe."));

        List<ImagemProduto> imagens = imagemProdutoRepository.findByProduto_Id(id);
        List<String> links = imagens.stream().map(ImagemProduto::getLink).collect(Collectors.toList());

        Double mediaNotas = opiniaoRepository.mediaNotasPorProduto(id);
        Integer totalNotas = opiniaoRepository.totalNotasPorProduto(id);

        List<PerguntaDto> perguntas = produto.getPerguntas().stream().map(pergunta -> {
            return new PerguntaDto(pergunta.getId(), pergunta.getTitulo(), pergunta.getUsuario().getId(),
                    pergunta.getProduto().getId(), pergunta.getDataCadastro());
        }).collect(Collectors.toList());

        List<OpiniaoDto> opinioes = produto.getOpinioes().stream().map(opiniao -> {
            return new OpiniaoDto(opiniao.getId(), opiniao.getTitulo(), opiniao.getNota(), opiniao.getDescricao(),
                    opiniao.getProduto().getId(), opiniao.getUsuario().getId());
        }).collect(Collectors.toList());

        return new ProdutoDetalheDto(links, produto.getNome(), produto.getValor(), produto.getCaracteristicas(),
                produto.getDescricao(), mediaNotas, totalNotas, opinioes, perguntas);
    }

}
