package com.turini.rest.controller;

import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.validator.Validator;
import com.turini.domain.*;
import com.turini.domain.geographic.Coordinate;
import com.turini.rest.response.RESTResponse;
import com.turini.service.PropertiesService;

import javax.inject.Inject;

@Path("/properties")
@Controller
public class PropertiesController {

	private final Result result;
	private final Validator validator;
	private final PropertiesService properties;

	@Inject
	public PropertiesController(Result result,
			Validator validator, PropertiesService properties) {
		this.result = result;
		this.validator = validator;
		this.properties = properties;
	}

	protected PropertiesController() {
		this(null, null, null);
	}

	@Consumes("application/json")
	@Post("")
	public void create(Property property) {

		validator.validate(property)
			.onErrorUse(RESTResponse.class)
			.invalid(property);

		property = properties.create(property);
		result.use(RESTResponse.class).created(property);
	}

	@Get("/{id}")
	public void findBy(int id) {
		properties.findBy(id).ifPresentOrElse(
			property -> result.use(RESTResponse.class).found(property),
			result::notFound);
	}

	@Get("")
	public void findBy(int ax, int ay, int bx, int by) {
		var coordinateA = new Coordinate(ax, ay);
		var coordinateB = new Coordinate(bx, by);
		var properties = this.properties.findBy(coordinateA, coordinateB);
		if (properties.isEmpty()) {
			result.notFound();
			return;
		}
		result.use(RESTResponse.class).found(properties);
	}
}