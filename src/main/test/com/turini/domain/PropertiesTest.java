package com.turini.domain;

import org.junit.*;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class PropertiesTest {

	private Properties properties;
	private Property oneProperty;
	private Property otherProperty;

	@Before
	public void setUp() {
		oneProperty = new Property();
		otherProperty = new Property();
		properties = new Properties(List.of(oneProperty, otherProperty));
	}

	@Test
	public void should_set_total_properties_based_on_the_size_of_properties_list() {
		properties = new Properties(null);
		assertThat(properties.getTotalProperties(), is(0));

		properties = new Properties(List.of(oneProperty));
		assertThat(properties.getTotalProperties(), is(1));

		properties = new Properties(List.of(oneProperty, otherProperty));
		assertThat(properties.getTotalProperties(), is(2));
	}

	@Test
	public void should_return_a_copy_of_properties_list_to_ensure_encapsulation() {
		assertThat(properties.getTotalProperties(), is(2));
		assertThat(properties.getProperties(), hasSize(2));

		List<Property> copyList = properties.getProperties();
		copyList.add(new Property());

		assertThat(properties.getTotalProperties(), is(2));
		assertThat(properties.getProperties(), hasSize(2));
	}

	@Test
	public void should_return_if_properties_list_is_empty_or_not() {
		assertThat(properties.isEmpty(), is(false));
		properties = new Properties(null);
		assertThat(properties.isEmpty(), is(true));
	}
}