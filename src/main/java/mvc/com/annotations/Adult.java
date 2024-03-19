package mvc.com.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import mvc.com.annotations.validator.AdultValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {AdultValidator.class})
public @interface Adult {
    String message() default "Age must be greater than 18";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
