package com.social.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.social.dto.SocialLoginDTO;

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
