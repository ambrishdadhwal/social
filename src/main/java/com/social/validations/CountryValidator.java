package com.social.validations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotNull;

import com.social.common.Country;

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
