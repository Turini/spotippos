package com.turini.rest.response;

import br.com.caelum.vraptor.validator.Message;

import java.util.List;

public class ValidationError<T> {

	private final T property;
	private final String description;
	private final List<Message> errors;

	public ValidationError(T property, List<Message> errors) {
		this.property = property;
		this.errors = errors;
		this.description = "There are some validation errors";
	}
}