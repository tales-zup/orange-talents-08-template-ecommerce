package com.zup.ecommerce.opiniao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OpiniaoRepository extends JpaRepository<Opiniao, Long> {

    @Query(value = "SELECT AVG(o.nota) FROM Opiniao o WHERE o.produto.id = :idProduto")
    Double mediaNotasPorProduto(Long idProduto);

    @Query(value = "SELECT COUNT(o.nota) FROM Opiniao o WHERE o.produto.id = :idProduto")
    Integer totalNotasPorProduto(Long idProduto);
}
