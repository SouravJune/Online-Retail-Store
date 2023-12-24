package com.msd.inventory.validation.annotation;

import com.msd.inventory.validation.QuantityFlagValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = QuantityFlagValidator.class)
public @interface ValidQuantityFlag {
    String message() default "Invalid quantityFlag value";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

