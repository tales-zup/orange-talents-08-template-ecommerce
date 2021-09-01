package com.zup.ecommerce.commons.validation;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ValorUnicoValidador implements ConstraintValidator<ValorUnico, String> {

    private String domainAttribute;
    private Class<?> klass;
    @PersistenceContext
    private EntityManager em;

    @Override
    public void initialize(ValorUnico constraintAnnotation) {
        domainAttribute = constraintAnnotation.fieldName();
        klass = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        Query query = em.createQuery("select 1 from " + klass.getName() + " where " + domainAttribute + " = :value");
        query.setParameter("value", s);
        List<?> list = query.getResultList();
        Assert.state(list.size() <= 1, "Foi encontrado mais de um " + klass + " com o atributo " + domainAttribute);

        return list.isEmpty();
    }

}
