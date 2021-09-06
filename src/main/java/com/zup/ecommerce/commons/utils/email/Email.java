package com.zup.ecommerce.commons.utils.email;

public class Email {

    private String assunto;
    private String corpo;
    private String destinatario;

    public Email(String assunto, String corpo, String destinatario) {
        this.assunto = assunto;
        this.corpo = corpo;
        this.destinatario = destinatario;
    }
}
