package com.turini.service;

import com.turini.domain.*;
import com.turini.domain.Properties;
import com.turini.domain.geographic.Coordinate;
import com.turini.service.repository.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static java.util.Collections.emptyList;
import static java.util.List.of;
import static java.util.Optional.empty;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PropertiesServiceTest {

	@Mock private ProvincesRepository provincesRepository;
	@Mock private PropertiesRepository propertiesRepository;

	private PropertiesService service;

	@Before
	public void setUp() {
		service = new PropertiesService(propertiesRepository, provincesRepository);
	}

	@Test
	public void should_create_property_using_repository_and_setting_provinces() {
		Property property = new Property();
		service.create(property);
		verify(propertiesRepository).create(property);
		verify(provincesRepository).addFor(property);
	}

	@Test
	public void should_find_property_using_repository_and_setting_provinces() {
		when(propertiesRepository.findBy(1)).thenReturn(empty());
		service.findBy(1);

		verify(propertiesRepository).findBy(1);
		verifyZeroInteractions(provincesRepository);

		Optional<Property> property = Optional.of(new Property());
		when(propertiesRepository.findBy(1)).thenReturn(property);
		service.findBy(1);
		verify(provincesRepository).addFor(property.get());
	}

	@Test
	public void should_find_properties_in_area_using_repository_and_setting_provinces() {
		var coordinateA = new Coordinate(100, 0);
		var coordinateB = new Coordinate(0, 200);

		Properties properties = new Properties(emptyList());
		when(propertiesRepository.findInArea(coordinateA, coordinateB)).thenReturn(properties);

		service.findBy(coordinateA, coordinateB);
		verify(propertiesRepository).findInArea(coordinateA, coordinateB);
		verifyZeroInteractions(provincesRepository);

		Property property = new Property();
		properties = new Properties(of(property));
		when(propertiesRepository.findInArea(coordinateA, coordinateB)).thenReturn(properties);
		service.findBy(coordinateA, coordinateB);
		verify(provincesRepository).addFor(property);
	}
}