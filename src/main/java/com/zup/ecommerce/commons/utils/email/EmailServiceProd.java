package com.zup.ecommerce.commons.utils.email;

import com.zup.ecommerce.pergunta.Pergunta;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("prod")
@Service
public class EmailServiceProd implements EmailService {
    @Override
    public void send(Email email) {
        System.out.println("ENVIANDO EMAIL PARA PRODUCAO!");
    }

    @Override
    public Email construir(Pergunta pergunta) {
        return new Email("Nova Pergunta", pergunta.getTitulo(), pergunta.getUsuario().getLogin());
    }
}
