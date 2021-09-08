package com.zup.ecommerce.pagamento;

public class PagamentoDto {

    private Long idPagamento;
    private Long idCompra;
    private String status;

    public PagamentoDto(Long idPagamento, Long idCompra, String status) {
        this.idPagamento = idPagamento;
        this.idCompra = idCompra;
        this.status = status;
    }

    public Long getIdPagamento() {
        return idPagamento;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public String getStatus() {
        return status;
    }
}
