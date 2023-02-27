package com.social.profile.validations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;

@Documented
@Constraint(validatedBy = ValidateLoginImpl.class)
@ReportAsSingleViolation
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateLogin
{

	String message() default "Provide email or username , not both";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
