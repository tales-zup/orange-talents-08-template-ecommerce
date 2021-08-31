package com.zup.ecommerce.commons;

import org.springframework.stereotype.Service;

@Service
public class HashService {

    public String hash(String senha) {
        return String.valueOf(senha.hashCode());
    }

}
