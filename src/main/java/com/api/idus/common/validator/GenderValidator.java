package com.api.idus.common.validator;


import com.api.idus.common.annotataion.Gender;
import com.api.idus.common.enums.Genders;
import com.api.idus.common.utility.ObjectUtility;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GenderValidator implements ConstraintValidator<Gender, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(ObjectUtility.isEmpty(value)) {
            return true;
        }

        return Genders.isCorrectValue(value);
    }
}