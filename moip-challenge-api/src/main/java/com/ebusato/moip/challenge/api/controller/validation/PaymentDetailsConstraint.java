package com.ebusato.moip.challenge.api.controller.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = PaymentDetailsValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PaymentDetailsConstraint {
    String message() default "Invalid payment details for given type";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
