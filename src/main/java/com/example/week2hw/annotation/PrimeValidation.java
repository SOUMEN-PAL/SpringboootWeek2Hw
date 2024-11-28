package com.example.week2hw.annotation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PrimeValidator.class)
@Target({ElementType.FIELD , ElementType.METHOD})
public @interface PrimeValidation {
    String message() default "The number has to be prime";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
