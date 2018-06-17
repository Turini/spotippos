package com.turini.service.repository;

import com.turini.domain.Properties;
import com.turini.domain.*;
import com.turini.domain.geographic.*;
import org.junit.*;
import org.mockito.Mockito;

import java.util.*;

import static java.util.List.of;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.spy;

public class PropertiesRepositoryTest {

	private PropertiesRepository repository;

	@Before
	public void setUp() {
		repository = new PropertiesRepository();
	}

	@Test
	public void should_find_property_by_id_or_return_an_empty_optional() {

		Property newProperty = new Property();
		newProperty.setId(1);

		repository.bulkInsert(of(newProperty));

		assertThat(repository.findBy(1), is(Optional.of(newProperty)));
		assertThat(repository.findBy(2), is(Optional.empty()));
	}

	@Test
	public void should_return_the_first_property_found_if_id_duplicated_for_some_reason() {
		Property newProperty = new Property();
		newProperty.setId(1);

		Property anotherProperty = new Property();
		anotherProperty.setId(1);

		repository.bulkInsert(of(newProperty, anotherProperty));
		assertThat(repository.findBy(1), is(Optional.of(newProperty)));
	}

	@Test
	public void should_create_new_properties_auto_incrementing_the_id() {

		assertThat(repository.findBy(1), is(Optional.empty()));

		Property newProperty = new Property();
		repository.create(newProperty);
		assertThat(repository.findBy(1), is(Optional.of(newProperty)));

		assertThat(repository.findBy(2), is(Optional.empty()));

		Property anotherProperty = new Property();
		repository.create(anotherProperty);
		assertThat(repository.findBy(2), is(Optional.of(anotherProperty)));

		assertThat(repository.findBy(3), is(Optional.empty()));
	}

	@Test
	public void should_find_properties_on_given_coordinates() {

		var coordinateA = new Coordinate(100, 100);
		var coordinateB = new Coordinate(100, 100);

		Geographic geographic = new Geographic();

		Property newProperty = spy(new Property(geographic));
		newProperty.setLatitude(coordinateA.getLatitude());
		newProperty.setLongitude(coordinateA.getLongitude());

		Property anotherProperty = spy(new Property(geographic));
		anotherProperty.setLatitude(coordinateB.getLatitude());
		anotherProperty.setLongitude(coordinateB.getLongitude());

		Property farAwayProperty = spy(new Property(geographic));
		farAwayProperty.setLatitude(1400);
		farAwayProperty.setLongitude(1000);

		repository.bulkInsert(of(newProperty, anotherProperty, farAwayProperty));

		Properties inArea = repository.findInArea(coordinateA, coordinateB);

		Mockito.verify(newProperty).isBetween(coordinateA, coordinateB);
		Mockito.verify(anotherProperty).isBetween(coordinateA, coordinateB);
		Mockito.verify(farAwayProperty).isBetween(coordinateA, coordinateB);

		assertThat(inArea.getProperties(), hasSize(2));
		assertThat(inArea.getProperties(), containsInAnyOrder(newProperty, anotherProperty));
	}

	@Test(expected = IllegalStateException.class)
	public void should_allow_bulk_insertion_only_if_list_is_empty() {
		List<Property> properties = List.of(new Property());
		try {
			repository.bulkInsert(properties);
		} catch (Exception e) {
			Assert.fail("shouldn't have given exception, since the list is empty");
		}
		repository.bulkInsert(properties);
	}
}