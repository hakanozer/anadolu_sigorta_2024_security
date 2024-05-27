package com.works.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CityValidator.class)
public @interface CityValid {

    String message() default "City Valid Fail!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
