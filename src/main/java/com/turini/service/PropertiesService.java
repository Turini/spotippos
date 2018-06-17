package com.turini.service;

import com.turini.domain.Properties;
import com.turini.domain.*;
import com.turini.domain.geographic.Coordinate;
import com.turini.service.repository.*;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.Optional;

@Dependent
public class PropertiesService {

	private final PropertiesRepository propertiesRepository;
	private final ProvincesRepository provincesRepository;

	@Inject
	public PropertiesService(PropertiesRepository propertiesRepository,
			 ProvincesRepository provincesRepository) {
		this.propertiesRepository = propertiesRepository;
		this.provincesRepository = provincesRepository;
	}

	public Property create(Property property) {
		provincesRepository.addFor(property);
		return propertiesRepository.create(property);
	}

	public Optional<Property> findBy(int id) {
		return propertiesRepository.findBy(id).stream()
			.peek(provincesRepository::addFor).findFirst();
	}

	public Properties findBy(Coordinate coordinateA, Coordinate coordinateB) {
		var properties = propertiesRepository
			.findInArea(coordinateA, coordinateB);
		properties.stream().forEach(provincesRepository::addFor);
		return properties;
	}
}