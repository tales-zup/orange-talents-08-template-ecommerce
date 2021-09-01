package com.zup.ecommerce.commons.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    public static String toJson(Object object) throws JsonProcessingException {
        return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(object);
    }

}
