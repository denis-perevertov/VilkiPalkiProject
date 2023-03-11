package com.example.vilkipalki2.dto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    @Override
    public boolean isValid(String number, ConstraintValidatorContext cxt) {
        return number != null && number.matches("\\+380[0-9]{9}");
    }
}
