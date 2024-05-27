package com.works.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class CityValidator implements ConstraintValidator<CityValid, String> {

    String[] cities = {"istanbul", "ankara", "bursa"};

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return Arrays.asList(cities).contains(s);
    }

}
