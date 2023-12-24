package com.msd.inventory.validation.annotation;

import com.msd.inventory.validation.CategoryListValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CategoryListValidator.class)
public @interface ValidCategoryList {
    String message() default "Invalid category name in the list";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
