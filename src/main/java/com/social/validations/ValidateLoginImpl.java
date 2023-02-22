package com.social.validations;

import com.social.dto.SocialLoginDTO;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidateLoginImpl implements ConstraintValidator<ValidateLogin, SocialLoginDTO>
{

	@Override
	public boolean isValid(SocialLoginDTO value, ConstraintValidatorContext context)
	{
		if (!(value instanceof SocialLoginDTO))
		{
			throw new IllegalArgumentException("@ValidateLogin only applies to SocialLoginDTO objects");
		}

		String email = value.getEmail();
		String userName = value.getUserName();

		if ((email != null) && (userName != null))
		{
			return false;
		}

		return true;
	}

	@Override
	public void initialize(ValidateLogin constraintAnnotation)
	{

	}

}
