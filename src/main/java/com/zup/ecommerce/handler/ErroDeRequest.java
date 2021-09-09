package com.zup.ecommerce.handler;

public class ErroDeRequest {

    private String campo;
    private String erro;

    public ErroDeRequest(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }

}
