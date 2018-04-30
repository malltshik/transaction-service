package ru.malltshik.transferservice.validations.annatations;

import ru.malltshik.transferservice.validations.validators.ExistedEntityValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { ExistedEntityValidator.class })
@Documented
public @interface ExistedEntity {

    String message();

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    Class<?> target();
}