package com.social.profile.validations;

import java.util.Arrays;
import java.util.List;

import com.social.profile.common.Country;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CountryValidatorImpl implements ConstraintValidator<CountryValidator, Country>
{

	List<Country> valueList = null;

	@Override
	public boolean isValid(Country country, ConstraintValidatorContext context)
	{
		return valueList.contains(country);
	}

	@Override
	public void initialize(CountryValidator constraintAnnotation)
	{
		valueList = Arrays.asList(Country.values());

	}
}
