package ru.malltshik.transferservice.validations.validators;

import ru.malltshik.transferservice.repositories.AccountRepository;
import ru.malltshik.transferservice.validations.annatations.ExistedAccount;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.ws.rs.ext.Provider;

/**
 * Validator for checking account exist status
 */
@Provider
@Singleton
public class ExistedAccountValidator implements ConstraintValidator<ExistedAccount, Long> {

    @Inject
    private AccountRepository repository;

    @Override
    public void initialize(ExistedAccount constraintAnnotation) {
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (repository != null) return repository.getOne(value) != null;
        else return true;
    }
}
