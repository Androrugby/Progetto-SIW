package it.uniroma3.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.model.Utente;

@Component
public class UtenteValidator implements Validator{

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub

	}
	@Override
	public boolean supports(Class<?> aClass) {
		return Utente.class.equals(aClass);
	}
}

