package com.zup.ecommerce.compra;

public enum GatewayPagamento {

    PAGSEGURO("/pagseguro?returnId={idGeradoDaCompra}", "SUCESSO", "ERRO"),
    PAYPAL("/paypal?buyerId={idGeradoDaCompra}", "1", "0");

    private String url;
    private String statusSucesso;
    private String statusErro;

    GatewayPagamento(String url, String statusSucesso, String statusErro) {
        this.url = url;
        this.statusSucesso = statusSucesso;
        this.statusErro = statusErro;
    }

    public String getUrl() {
        return url;
    }

    public String getStatusSucesso() {
        return statusSucesso;
    }

    public String getStatusErro() {
        return statusErro;
    }
}
