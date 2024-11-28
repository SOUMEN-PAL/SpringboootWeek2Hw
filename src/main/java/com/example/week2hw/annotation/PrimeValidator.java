package com.example.week2hw.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PrimeValidator implements ConstraintValidator<PrimeValidation , Integer> {
    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        for(int i = 2 ; i*i <= integer ; i++){
            if(integer%i == 0){
                return false;
            }
        }
        return true;
    }
}
