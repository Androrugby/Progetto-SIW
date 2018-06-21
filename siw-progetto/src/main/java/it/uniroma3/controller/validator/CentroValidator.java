package it.uniroma3.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.model.Centro;

@Component
public class CentroValidator implements Validator{

	@Override
	public void validate(Object target, Errors errors) {
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
	}
	
	@Override
	public boolean supports(Class<?> aClass) {
		return Centro.class.equals(aClass);
	}
}
