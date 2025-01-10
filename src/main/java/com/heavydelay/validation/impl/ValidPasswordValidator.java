package com.heavydelay.validation.impl;

import com.heavydelay.validation.ValidPassword;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidPasswordValidator implements ConstraintValidator<ValidPassword, String>{

    //Obligatorio minusculas y numeros
    private static final String PASSWORD_PATTERN = 
            "^(?=.*[a-z])(?=.*\\d)[A-Za-z\\d@$!%*?&]{6,}$";

    @Override
    public void initialize(ValidPassword constrintAnnotation){

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        return value != null && value.matches(PASSWORD_PATTERN);
    }
}
