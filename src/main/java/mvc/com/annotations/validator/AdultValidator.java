package mvc.com.annotations.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import mvc.com.annotations.Adult;

public class AdultValidator implements ConstraintValidator<Adult, Integer> { //название аннотации и тип поля
    @Override
    public void initialize(Adult constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value > 18;
    }

}
