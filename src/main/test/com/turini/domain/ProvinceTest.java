package com.turini.domain;

import com.turini.domain.geographic.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.spy;

@RunWith(MockitoJUnitRunner.class)
public class ProvinceTest {

	private Province province;
	private Geographic geographic;

	@Before
	public void setUp() {
		geographic = spy(new Geographic());
		province = new Province(geographic);
	}

	@Test
	public void should_call_geographic_to_know_if_it_contains_coordinates() {

		var coordinateA = new Coordinate(0, 100);
		var coordinateB = new Coordinate(400, 0);
		var boundaries = new Boundaries(coordinateA, coordinateB);
		province.setBoundaries(boundaries);

		Coordinate coordinate = new Coordinate(100, 200);
		province.contains(coordinate);

		Mockito.verify(geographic).contains(coordinate, boundaries);
	}
}