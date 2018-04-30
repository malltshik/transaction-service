package ru.malltshik.transferservice.validations.validators;

import ru.malltshik.transferservice.validations.annatations.ExistedEntity;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistedEntityValidator implements ConstraintValidator<ExistedEntity, Long> {

    @Inject
    private EntityManager em;

    private Class<?> target;

    @Override
    public void initialize(ExistedEntity constraintAnnotation) {
        target = constraintAnnotation.target();
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return em.find(target, value) != null;
    }
}
