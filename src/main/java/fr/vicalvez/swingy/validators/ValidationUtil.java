package fr.vicalvez.swingy.validators;

import fr.vicalvez.swingy.controller.RunMode;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import javax.swing.*;
import java.util.Set;


public class ValidationUtil {

	public static <T> boolean isInvalid(T object)
	{
		Set<ConstraintViolation<T>> violations = getValidator().validate(object);
		return !violations.isEmpty();
	}

	public static <T> void printValidationError(T object, JLabel errorLabel, RunMode mode)
	{
		Set<ConstraintViolation<T>> violations = getValidator().validate(object);
		violations.forEach(violation -> {
			if (mode == RunMode.CONSOLE)
				System.out.println(violation.getPropertyPath() + ": " + violation.getMessage());
			else {
				errorLabel.setText(violation.getMessage());
			}
		});
	}

	private static Validator getValidator() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		return factory.getValidator();
	}

}
