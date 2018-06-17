package com.turini.rest.controller;

import br.com.caelum.vraptor.*;

import javax.inject.Inject;

@Controller
public class IndexController {

	private final Result result;

	@Inject
	public IndexController(Result result) {
		this.result = result;
	}

	protected IndexController() {
		this(null);
	}

	@Path("/")
	public void index() {
	}
}