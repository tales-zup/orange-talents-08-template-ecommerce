package com.zup.ecommerce.caracteristica;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaracteristicaRepository extends JpaRepository<Caracteristica, Long> {

    List<Caracteristica> findByProduto_Id(Long idProduto);

}
