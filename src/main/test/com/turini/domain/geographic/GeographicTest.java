package com.turini.domain.geographic;

import org.junit.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class GeographicTest {

	private Geographic geographic;
	private Coordinate coordinate;

	@Before
	public void setUp() {
		this.geographic = new Geographic();
		this.coordinate = new Coordinate(800, 600);
	}

	@Test
	public void should_return_true_if_coordinate_is_in_boundaries_inclusive() {

		var upperLeft = new Coordinate(0, 1000);
		var bottomRight = new Coordinate(1400, 0);

		var boundaries = new Boundaries(upperLeft, bottomRight);

		assertThat(
			geographic.contains(coordinate, boundaries),
			is(true)
		);

		bottomRight = new Coordinate(800, 0);
		boundaries = new Boundaries(upperLeft, bottomRight);

		assertThat(
			geographic.contains(coordinate, boundaries),
			is(true)
		);

		bottomRight = new Coordinate(800, 600);
		boundaries = new Boundaries(upperLeft, bottomRight);

		assertThat(
			geographic.contains(coordinate, boundaries),
			is(true)
		);

		upperLeft = new Coordinate(800, 1000);
		boundaries = new Boundaries(upperLeft, bottomRight);

		assertThat(
			geographic.contains(coordinate, boundaries),
			is(true)
		);

		upperLeft = new Coordinate(800, 600);
		boundaries = new Boundaries(upperLeft, bottomRight);

		assertThat(
			geographic.contains(coordinate, boundaries),
			is(true)
		);
	}

	@Test
	public void should_return_false_if_coordinate_is_out_of_boundaries() {

		var upperLeft = new Coordinate(0, 1000);
		var bottomRight = new Coordinate(799, 600);
		var boundaries = new Boundaries(upperLeft, bottomRight);

		assertThat(
			geographic.contains(coordinate, boundaries),
			is(false)
		);

		bottomRight = new Coordinate(1400, 601);
		boundaries = new Boundaries(upperLeft, bottomRight);

		assertThat(
			geographic.contains(coordinate, boundaries),
			is(false)
		);

		upperLeft = new Coordinate(801, 1000);
		bottomRight = new Coordinate(1400, 0);
		boundaries = new Boundaries(upperLeft, bottomRight);

		assertThat(
			geographic.contains(coordinate, boundaries),
			is(false)
		);

		upperLeft = new Coordinate(0, 599);
		boundaries = new Boundaries(upperLeft, bottomRight);

		assertThat(
			geographic.contains(coordinate, boundaries),
			is(false)
		);
	}
}