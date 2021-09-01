package com.zup.ecommerce.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping
    @Transactional
    public CategoriaDto cadastrarCategoria(@RequestBody @Valid CategoriaRequest request) {
        Categoria categoria = request.toModel(categoriaRepository);
        categoria = categoriaRepository.save(categoria);
        return new CategoriaDto(categoria.getId(), categoria.getNome(), categoria.getCategoriaMae());
    }

}
