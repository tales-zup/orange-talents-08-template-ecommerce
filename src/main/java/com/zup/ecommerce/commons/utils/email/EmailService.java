package com.zup.ecommerce.commons.utils.email;

import com.zup.ecommerce.pergunta.Pergunta;

public interface EmailService {

    void send(Email email);

    Email construir(Pergunta pergunta);
}
