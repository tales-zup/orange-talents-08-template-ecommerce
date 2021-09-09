package com.zup.ecommerce.apiexterna;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(name = "sistema-externo", url = "${feign.client.api-externa}")
public interface ApiExterna {

    @PostMapping(value = "/rankings")
    String comunicaSistemaRanking(@RequestBody @Valid SistemaRankingRequest request);

    @PostMapping(value = "/notas-fiscais")
    String comunicaSistemaNotasFiscais(@RequestBody @Valid SistemaNotasRequest request);

}
