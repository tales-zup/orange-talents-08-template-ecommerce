package com.zup.ecommerce.commons.utils.email;

import com.zup.ecommerce.compra.Compra;
import com.zup.ecommerce.pergunta.Pergunta;

public interface EmailService {

    void enviar(Email email);

    Email construir(Pergunta pergunta);

    Email construir(Compra compra);
}
