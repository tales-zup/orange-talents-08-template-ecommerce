package com.zup.ecommerce.commons.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ExistsIdValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsId {

    String message() default "Registro duplicado.";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

    String nomeDoCampo();

    Class<?> classe();

    boolean opcional() default false;

}
