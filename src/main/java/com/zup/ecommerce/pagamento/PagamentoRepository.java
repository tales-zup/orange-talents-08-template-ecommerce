package com.zup.ecommerce.pagamento;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

    List<Pagamento> findByStatusCompra_Id(StatusPagamento status, Long idCompra);

}
