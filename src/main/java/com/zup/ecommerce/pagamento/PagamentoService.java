package com.zup.ecommerce.pagamento;

import com.zup.ecommerce.compra.Compra;
import com.zup.ecommerce.compra.CompraRepository;
import com.zup.ecommerce.compra.GatewayPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    public PagamentoDto cadastrarPagamento(Long idCompra, GatewayPagamento gatewayPagamento) {

        Compra compra = compraRepository.findById(idCompra).orElseThrow(
                () -> new IllegalArgumentException("Essa compra não existe"));

        Pagamento pagamento = new Pagamento(compra, StatusPagamento.SUCESSO);
        pagamento = pagamentoRepository.save(pagamento);
        return new PagamentoDto(pagamento.getId(), compra.getId(), gatewayPagamento.toString());
    }

}
