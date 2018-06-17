package com.turini.rest.response;

import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.validator.Validator;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import static br.com.caelum.vraptor.view.Results.*;

@RequestScoped
public class RESTResponse implements View {

	private final Result result;
	private final Validator validator;

	@Inject
	public RESTResponse(Result result, Validator validator) {
		this.result = result;
		this.validator = validator;
	}

	protected RESTResponse() {
		this(null, null);
	}

	public <T> void created(T object) {
		result.use(http()).setStatusCode(201);
		result.use(json()).withoutRoot()
			.from(object).recursive().serialize();
	}

	public <T> void invalid(T object) {
		result.use(http()).setStatusCode(400);
		var errors = validator.getErrors();
		validator.onErrorUse(json()).withoutRoot()
			.from(new ValidationError(object, errors))
			.recursive().serialize();
	}

	public <T> void found(T object) {
		result.use(json()).withoutRoot()
			.from(object).recursive().serialize();
	}
}