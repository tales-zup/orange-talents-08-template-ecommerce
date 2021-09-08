package com.zup.ecommerce.pagamento;

import com.zup.ecommerce.compra.GatewayPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping("/paypal/{idCompra}")
    public PagamentoDto cadastrarPagamentoPayPal(@PathVariable("idCompra") Long idCompra) {
        return pagamentoService.cadastrarPagamento(idCompra, GatewayPagamento.PAYPAL);
    }

    @PostMapping("/pagseguro/{idCompra}")
    public PagamentoDto cadastrarPagamentoPagSeguro(@PathVariable("idCompra") Long idCompra) {
        return pagamentoService.cadastrarPagamento(idCompra, GatewayPagamento.PAGSEGURO);
    }

}
