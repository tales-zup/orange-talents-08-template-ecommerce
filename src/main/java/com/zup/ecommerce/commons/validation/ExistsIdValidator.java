package com.zup.ecommerce.commons.validation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistsIdValidator implements ConstraintValidator<ExistsId, Long> {

    private String atributo;
    private Class<?> classe;
    private boolean opcional;
    @PersistenceContext
    private EntityManager em;

    @Override
    public void initialize(ExistsId constraintAnnotation) {
        atributo = constraintAnnotation.nomeDoCampo();
        classe = constraintAnnotation.classe();
        opcional = constraintAnnotation.opcional();
    }

    @Override
    public boolean isValid(Long valor, ConstraintValidatorContext constraintValidatorContext) {

        if(opcional && valor == null)
            return true;

        Query query = em.createQuery("select 1 from " + classe.getName() + " where " + atributo + " = :value");
        query.setParameter("value", valor);
        List<?> list = query.getResultList();

        return !list.isEmpty();
    }
}
