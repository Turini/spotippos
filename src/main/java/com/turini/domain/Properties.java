package com.turini.domain;

import java.util.*;
import java.util.stream.*;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

public class Properties {

	private final Integer totalProperties;
	private final List<Property> properties;

	public Properties(List<Property> properties) {
		this.properties = Optional.ofNullable(properties).orElse(emptyList());
		totalProperties = this.properties.size();
	}

	public Integer getTotalProperties() {
		return totalProperties;
	}

	public List<Property> getProperties() {
		return stream().collect(toList());
	}

	public Stream<Property> stream() {
	    return properties.stream();
	}

	public boolean isEmpty() {
		return !stream().findAny().isPresent();
	}

	@Override
	public String toString() {
		return "Properties{" +
			"totalProperties=" + totalProperties +
			", properties=" + properties +
		'}';
	}
}
