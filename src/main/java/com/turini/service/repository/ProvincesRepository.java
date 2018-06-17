package com.turini.service.repository;

import com.turini.domain.*;

import javax.enterprise.context.ApplicationScoped;
import java.util.*;

import static java.util.Collections.emptyMap;

@ApplicationScoped
public class ProvincesRepository {

	private Map<String, Province> provinces = emptyMap();

	public void addFor(Property property) {
		var coordinate = property.getCoordinate();
		provinces.entrySet().stream()
			.filter(e -> e.getValue().contains(coordinate))
			.forEach(e-> property.addProvince(e.getKey()));
	}

	public void bulkInsert(Map<String,Province> provinces) {
		if (!this.provinces.isEmpty()) {
			throw new IllegalStateException(
				"bulk insertion is only allowed when the map is empty");
		}
		this.provinces = provinces;
	}
}