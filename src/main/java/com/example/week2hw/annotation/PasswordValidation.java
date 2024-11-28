package com.example.week2hw.annotation;

import jakarta.validation.Constraint;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.RuntimeType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD , ElementType.METHOD})
@Constraint(validatedBy = PasswordValidator.class)
public @interface PasswordValidation {
}
