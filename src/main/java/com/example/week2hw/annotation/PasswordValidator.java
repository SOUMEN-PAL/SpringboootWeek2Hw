package com.example.week2hw.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordValidation ,String> {

    int upperCaseCnt = 0;
    int lowerCaseCnt = 0;
    int specialCharacter = 0;
    int length = 0;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        length = s.length();
        boolean isValid = false;

        if(length < 10){
            return false;
        }

        for(int i = 0 ; i<length ; i++){
            char c = s.charAt(i);
            if (Character.isUpperCase(c)) {
                upperCaseCnt++;
            } else if (Character.isLowerCase(c)) {
                lowerCaseCnt++;
            } else if (!Character.isLetterOrDigit(c)) { // Special character check
                specialCharacter++;
            }
        }
        if(upperCaseCnt < 1 || lowerCaseCnt < 1 || specialCharacter < 1){
            return false;
        }
        return true;
    }
}
