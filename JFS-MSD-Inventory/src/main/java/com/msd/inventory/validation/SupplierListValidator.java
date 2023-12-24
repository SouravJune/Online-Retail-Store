package com.msd.inventory.validation;

import com.msd.inventory.validation.annotation.ValidSupplierList;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class SupplierListValidator implements ConstraintValidator<ValidSupplierList, List<String>> {
    @Override
    public void initialize(ValidSupplierList constraintAnnotation) {
    }

    @Override
    public boolean isValid(List<String> value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        for (String supplier : value) {
            if (supplier == null || supplier.length() < 2
                    || supplier.length() > 30 || !supplier.matches("^[a-zA-Z ]+$")) {
                return false;
            }
        }

        return true;
    }
}
