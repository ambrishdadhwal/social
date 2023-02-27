package com.social.profile.validations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.social.profile.common.Country;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.NotNull;

@Documented
@Constraint(validatedBy = CountryValidatorImpl.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@NotNull(message = "Value cannot be null")
@ReportAsSingleViolation
public @interface CountryValidator
{

	Country acceptedValue() default Country.INDIA;

	String message() default "Value is not valid";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
