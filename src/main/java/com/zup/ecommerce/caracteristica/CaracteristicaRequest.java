package com.zup.ecommerce.caracteristica;

import javax.validation.constraints.NotBlank;

public class CaracteristicaRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
