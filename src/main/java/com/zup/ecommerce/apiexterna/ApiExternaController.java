package com.zup.ecommerce.apiexterna;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-externa")
public class ApiExternaController {

    @PostMapping("/rankings")
    public String conectarSistemaRanking(@RequestBody SistemaRankingRequest request) {
        System.out.println("CONECTADO NO SISTEMA DE RANKING!");
        return request.toString();
    }

}
