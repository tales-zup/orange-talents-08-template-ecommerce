package com.zup.ecommerce.pagamento;

import com.zup.ecommerce.compra.Compra;
import com.zup.ecommerce.compra.CompraRepository;
import com.zup.ecommerce.compra.GatewayPagamento;
import com.zup.ecommerce.compra.StatusCompra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PagamentoService {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Transactional
    public PagamentoDto cadastrarPagamento(Long idCompra, GatewayPagamento gatewayPagamento) {

        Compra compra = compraRepository.findById(idCompra).orElseThrow(
                () -> new IllegalArgumentException("Essa compra não existe"));

        List<Pagamento> pagamentos = pagamentoRepository.findByStatusCompra_Id(StatusPagamento.SUCESSO, idCompra);
        if(!pagamentos.isEmpty())
            throw new IllegalArgumentException("Essa compra já tem um pagamento concluído.");

        Pagamento pagamento = new Pagamento(compra, StatusPagamento.SUCESSO);
        compra.setStatus(StatusCompra.SUCESSO);
        compraRepository.save(compra);
        pagamento = pagamentoRepository.save(pagamento);
        return new PagamentoDto(pagamento.getId(), compra.getId(), gatewayPagamento.getStatusSucesso());

    }

}
