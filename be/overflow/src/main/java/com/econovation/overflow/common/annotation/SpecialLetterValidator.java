package com.econovation.overflow.common.annotation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpecialLetterValidator implements ConstraintValidator<SpecialLetter, String> {

	private static final String SPECIAL_LETTER_PATTERN = ".*[!@#$%^&*(),.?\":{}|<>].*";
	private Pattern pattern;

	@Override
	public void initialize(SpecialLetter constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
		pattern = Pattern.compile(SPECIAL_LETTER_PATTERN);
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
