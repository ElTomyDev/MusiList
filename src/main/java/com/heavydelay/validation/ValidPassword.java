package com.heavydelay.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.heavydelay.validation.impl.ValidPasswordValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = ValidPasswordValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
    String message() default "'password' must be at least 6 characters long, a lowercase letter and a number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
