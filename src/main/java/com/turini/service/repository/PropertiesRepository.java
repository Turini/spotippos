package com.turini.service.repository;

import com.turini.domain.Properties;
import com.turini.domain.*;
import com.turini.domain.geographic.Coordinate;

import javax.enterprise.context.ApplicationScoped;
import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class PropertiesRepository {

	private List<Property> properties = new ArrayList<>();

	public Optional<Property> findBy(int id) {
	    return properties
			.stream()
			.filter(property -> property.getId().equals(id))
			.findFirst();
	}

	public Property create(Property property) {
		property.setId(generateNextId());
		properties.add(property);
		return property;
	}

	public Properties findInArea(
			Coordinate coordinateA, Coordinate coordinateB) {

		return new Properties(this.properties
			.stream()
			.filter(p -> p.isBetween(coordinateA, coordinateB))
			.collect(Collectors.toList()));
	}

	public void bulkInsert(List<Property> properties) {
		if (!this.properties.isEmpty()) {
			throw new IllegalStateException(
				"bulk insertion is only allowed when the list is empty");
		}
		this.properties = properties;
	}

	/**
	 * the pain of not using a database. Shame on me
	 */
	private Integer generateNextId() {
		return properties.stream().mapToInt(Property::getId).max().orElse(0) +1;
	}
}