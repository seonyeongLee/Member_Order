package com.api.idus.common.annotataion;

import com.api.idus.common.validator.GenderValidator;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GenderValidator.class)
public @interface Gender {
    String message() default "올바르지 않은 성별 입니다.";
    Class[] groups() default {};
    Class[] payload() default {};
}