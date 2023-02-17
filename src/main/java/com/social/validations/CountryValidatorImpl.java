package com.social.validations;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.social.common.Country;

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
