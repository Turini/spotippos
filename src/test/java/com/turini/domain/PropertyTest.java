package com.turini.domain;

import com.turini.domain.geographic.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.spy;

@RunWith(MockitoJUnitRunner.class)
public class PropertyTest {

	private Property property;
	private Geographic geographic;

	@Before
	public void setUp() {
		geographic = spy(new Geographic());
		property = new Property(geographic);
	}

	@Test
	public void should_call_geographic_to_know_if_is_between_coordinates() {
		property.setLatitude(100);
		property.setLongitude(800);
		var coordinate = property.getCoordinate();

		var coordinateA = new Coordinate(0, 100);
		var coordinateB = new Coordinate(400, 0);
		var boundaries = new Boundaries(coordinateA, coordinateB);

		property.isBetween(coordinateA, coordinateB);
		Mockito.verify(geographic).contains(coordinate, boundaries);
	}

	@Test
	public void should_return_coordinate_instance_with_its_latitude_and_longitude() {
		property.setLatitude(100);
		property.setLongitude(800);
		var coordinate = property.getCoordinate();
		assertThat(coordinate.getLatitude(), is(100));
		assertThat(coordinate.getLongitude(), is(800));
	}
}