package com.zup.ecommerce.compra;

public enum GatewayPagamento {

    PAGSEGURO("/pagseguro?returnId={idGeradoDaCompra}"),
    PAYPAL("/paypal?buyerId={idGeradoDaCompra}");

    private String url;

    GatewayPagamento(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
