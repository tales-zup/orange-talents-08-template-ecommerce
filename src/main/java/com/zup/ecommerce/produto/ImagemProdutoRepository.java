package com.zup.ecommerce.produto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImagemProdutoRepository extends JpaRepository<ImagemProduto, Long> {

    List<ImagemProduto> findByProduto_Id(Long id);
}
