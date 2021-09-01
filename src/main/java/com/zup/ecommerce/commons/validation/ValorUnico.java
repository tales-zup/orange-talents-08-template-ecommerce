package com.zup.ecommerce.commons.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValorUnicoValidador.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValorUnico {

    String message() default "Registro duplicado.";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

    String fieldName();

    Class<?> domainClass();

}