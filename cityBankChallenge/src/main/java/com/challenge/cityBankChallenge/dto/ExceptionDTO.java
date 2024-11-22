package com.challenge.cityBankChallenge.dto;

import org.springframework.validation.FieldError;

public record ExceptionDTO(String field, String error) {
	
	public ExceptionDTO(FieldError error) {
		this(error.getField(), error.getDefaultMessage());
	}
}
