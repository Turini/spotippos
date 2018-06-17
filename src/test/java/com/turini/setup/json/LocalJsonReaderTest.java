package com.turini.setup.json;

import com.turini.domain.Properties;
import org.junit.*;

import static java.math.BigDecimal.valueOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class LocalJsonReaderTest {

	private static final String JSON = "test-properties.json";
	private LocalJsonReader reader;

	@Before
	public void setUp() {
		reader = new LocalJsonReader();
	}

	@Test(expected = IllegalArgumentException.class)
	public void should_fail_if_file_name_is_null() {
		reader.read(null, Properties.class);
	}

	@Test(expected = IllegalArgumentException.class)
	public void should_fail_if_file_name_is_empty() {
		reader.read(" ", Properties.class);
	}

	@Test(expected = RuntimeException.class)
	public void should_fail_if_file_content_is_not_found() {
		reader.read("invalid-content.json", Properties.class);
	}

	@Test
	public void should_read_json_file_and_project_its_content() {

		Properties properties = reader.read(JSON, Properties.class);

		assertThat(properties.stream().count(), is(1l));
		assertThat(properties.getTotalProperties(), is(1));

		var property = properties.stream().findFirst().orElse(null);

		assertNotNull("property list is empty", property);

		assertThat(property.getId(), is(1));
		assertThat(property.getTitle(), is("Imóvel código 1"));
		assertThat(property.getPrice(), is(valueOf(643000)));
		assertThat(property.getDescription(), is("Laboris quis quis"));
		assertThat(property.getLatitude(), is(1257));
		assertThat(property.getLongitude(), is(928));
		assertThat(property.getBeds(), is(3));
		assertThat(property.getBaths(), is(2));
		assertThat(property.getSquareMeters(), is(61));
	}
}