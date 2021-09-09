package com.zup.ecommerce.pagamento;

import com.zup.ecommerce.apiexterna.ApiExterna;
import com.zup.ecommerce.apiexterna.SistemaNotasRequest;
import com.zup.ecommerce.apiexterna.SistemaRankingRequest;
import com.zup.ecommerce.commons.utils.email.Email;
import com.zup.ecommerce.commons.utils.email.EmailService;
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

    @Autowired
    private ApiExterna apiExterna;

    @Autowired
    private EmailService emailService;

    @Transactional
    public PagamentoDto cadastrarPagamento(Long idCompra, GatewayPagamento gatewayPagamento) {

        Compra compra = compraRepository.findById(idCompra).orElseThrow(
                () -> new IllegalArgumentException("Essa compra não existe."));

        List<Pagamento> pagamentos = pagamentoRepository.findByStatusAndCompra_Id(StatusPagamento.SUCESSO, idCompra);
        if(!pagamentos.isEmpty())
            throw new IllegalArgumentException("Essa compra já tem um pagamento concluído.");

        apiExterna.comunicaSistemaRanking(new SistemaRankingRequest(compra.getId(), compra.getProduto().getUsuario().getId()));
        apiExterna.comunicaSistemaNotasFiscais(new SistemaNotasRequest(compra.getId(), compra.getUsuario().getId()));

        Pagamento pagamento = new Pagamento(compra, StatusPagamento.SUCESSO);
        compra.setStatus(StatusCompra.SUCESSO);
        compraRepository.save(compra);
        pagamento = pagamentoRepository.save(pagamento);
        Email email = emailService.construir(pagamento);
        emailService.enviar(email);
        return new PagamentoDto(pagamento.getId(), compra.getId(), gatewayPagamento.getStatusSucesso());

    }

}
