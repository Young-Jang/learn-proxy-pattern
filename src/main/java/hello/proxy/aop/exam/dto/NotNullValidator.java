package hello.proxy.aop.exam.dto;
import hello.proxy.aop.exam.annotation.NotNull;

import javax.validation.*;

public class NotNullValidator implements ConstraintValidator<NotNull, Object> {

    private Class<? extends RuntimeException> exceptionClass;

    @Override
    public void initialize(NotNull constraintAnnotation) {
        exceptionClass = constraintAnnotation.exception();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            String message = context.getDefaultConstraintMessageTemplate();
            RuntimeException exception;
            try {
                exception = exceptionClass.getDeclaredConstructor(String.class).newInstance(message);
            } catch (Exception ex) {
                exception = new IllegalArgumentException(message);
            }
            throw exception;
        }
        return true;
    }
}