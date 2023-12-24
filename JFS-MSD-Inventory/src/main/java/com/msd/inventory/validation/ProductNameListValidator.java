package com.msd.inventory.validation;

import com.msd.inventory.validation.annotation.ValidProductNameList;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class ProductNameListValidator implements ConstraintValidator<ValidProductNameList, List<String>> {

    @Override
    public void initialize(ValidProductNameList constraintAnnotation) {
    }

    @Override
    public boolean isValid(List<String> value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        for (String productName : value) {
            if (productName == null || !productName.matches("^[a-zA-Z0-9 ]+$")
                    || productName.length() < 2 || productName.length() > 50) {
                return false;
            }
        }

        return true;
    }
}
