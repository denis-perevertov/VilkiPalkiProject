package com.example.vilkipalki2.dto;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneNumberValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumber {
    String message() default "Неправильный формат телефона!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
