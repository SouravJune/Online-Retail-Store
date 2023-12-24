package com.msd.inventory.validation;

import com.msd.inventory.validation.annotation.ValidCategoryList;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class CategoryListValidator implements ConstraintValidator<ValidCategoryList, List<String>> {

    @Override
    public void initialize(ValidCategoryList constraintAnnotation) {
    }

    @Override
    public boolean isValid(List<String> value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        for (String category : value) {
            if (category == null || category.length() < 2 || category.length() > 30) {
                return false;
            }
        }

        return true;
    }
}
