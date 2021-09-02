package com.zup.ecommerce.produto;

import com.zup.ecommerce.caracteristica.Caracteristica;
import com.zup.ecommerce.caracteristica.CaracteristicaRepository;
import com.zup.ecommerce.caracteristica.CaracteristicaRequest;
import com.zup.ecommerce.categoria.CategoriaRepository;
import com.zup.ecommerce.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CaracteristicaRepository caracteristicaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

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

}
