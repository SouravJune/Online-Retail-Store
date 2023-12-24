package com.msd.inventory.validation;

import com.msd.inventory.enumclass.QuantityFlag;
import com.msd.inventory.validation.annotation.ValidQuantityFlag;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class QuantityFlagValidator implements ConstraintValidator<ValidQuantityFlag, QuantityFlag> {

    @Override
    public void initialize(ValidQuantityFlag constraintAnnotation) {
    }

    @Override
    public boolean isValid(QuantityFlag value, ConstraintValidatorContext context) {
        return value == null || value == QuantityFlag.EXACT_VALUE || value == QuantityFlag.IN_RANGE;
    }
}
