package com.econovation.overflow.common.annotation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class UpperCaseValidator implements ConstraintValidator<UpperCase, String> {

	private Pattern pattern;
	private static final String UPPER_CASE_PATTERN = ".*[A-Z].*";

	@Override
	public void initialize(UpperCase constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
		pattern = Pattern.compile(UPPER_CASE_PATTERN);
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return false;
		}

		Matcher matcher = pattern.matcher(value);

		return matcher.matches();
	}
}
