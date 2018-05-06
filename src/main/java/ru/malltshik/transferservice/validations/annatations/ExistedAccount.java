package ru.malltshik.transferservice.validations.annatations;

import ru.malltshik.transferservice.validations.validators.ExistedAccountValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ExistedAccountValidator.class})
@Documented
public @interface ExistedAccount {

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}