package com.zup.ecommerce.compra;

public enum GatewayPagamento {

    PAGSEGURO("pagseguro.com?returnId={idGeradoDaCompra}&redirectUrl={urlRetornoAppPosPagamento}"),
    PAYPAL("paypal.com?buyerId={idGeradoDaCompra}&redirectUrl={urlRetornoAppPosPagamento}");

    private String url;

    GatewayPagamento(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
