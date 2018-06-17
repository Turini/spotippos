package com.turini.service.repository;

import com.turini.domain.*;
import com.turini.domain.geographic.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static java.util.Map.of;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.spy;

@RunWith(MockitoJUnitRunner.class)
public class ProvincesRepositoryTest {

	private ProvincesRepository repository;
	private Map<String, Province> provinceMap;
	private Province gode;
	private Province scavy;

	@Before
	public void setUp() {
		repository = new ProvincesRepository();

		Geographic geographic = new Geographic();
		gode = spy(new Province(geographic));
		gode.setBoundaries(new Boundaries(
				new Coordinate(0, 1000),
				new Coordinate(600, 500)));

		scavy = spy(new Province(geographic));
		scavy.setBoundaries(new Boundaries(
				new Coordinate(0, 500),
				new Coordinate(600, 0)));

		provinceMap = of("Gode", gode, "Scavy", scavy);
	}

	@Test
	public void should_add_provinces_for_given_property() {
		Property property = new Property();
		property.setLatitude(100);
		property.setLongitude(200);
		Coordinate coordinate = property.getCoordinate();

		repository.bulkInsert(provinceMap);

		repository.addFor(property);

		Mockito.verify(gode).contains(coordinate);
		Mockito.verify(scavy).contains(coordinate);

		Set<String> provinces = property.getProvinces();

		assertThat(provinces, hasSize(1));
		assertThat(provinces, contains("Scavy"));
	}

	@Test(expected = IllegalStateException.class)
	public void should_allow_bulk_insertion_only_if_list_is_empty() {
		Map<String, Province> provinceMap = of("Gode", new Province());
		try {
			repository.bulkInsert(provinceMap);
		} catch (Exception e) {
			Assert.fail("shouldn't have given exception, since the map is empty");
		}
		repository.bulkInsert(provinceMap);
	}
}