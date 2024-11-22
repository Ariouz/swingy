package fr.vicalvez.swingy.validators;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

public class ValidationUtil {

	public static <T> boolean isInvalid(T object)
	{
		Set<ConstraintViolation<T>> violations = getValidator().validate(object);
		return !violations.isEmpty();
	}

	public static <T> void printValidationError(T object)
	{
		Set<ConstraintViolation<T>> violations = getValidator().validate(object);
		// todo display instead of printing for GUI mode
		violations.forEach(violation ->
				System.out.println(violation.getPropertyPath() + ": " + violation.getMessage())
		);
	}

	private static Validator getValidator() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		return factory.getValidator();
	}

}
