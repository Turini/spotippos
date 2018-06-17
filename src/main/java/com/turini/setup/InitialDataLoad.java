package com.turini.setup;

import br.com.caelum.vraptor.events.VRaptorInitialized;
import com.google.common.reflect.TypeToken;
import com.turini.domain.*;
import com.turini.service.repository.*;
import com.turini.setup.json.JsonReader;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.Map;

@ApplicationScoped
public class InitialDataLoad {

	public static final String PROVINCES_FILE = "provinces.json";
	public static final String PROPERTIES_FILE = "properties.json";

	private final JsonReader reader;
	private final ProvincesRepository provincesRepository;
	private final PropertiesRepository propertiesRepository;

	@Inject
	public InitialDataLoad(JsonReader reader, ProvincesRepository provincesRepository,
		   PropertiesRepository propertiesRepository) {
		this.reader = reader;
		this.provincesRepository = provincesRepository;
		this.propertiesRepository = propertiesRepository;
	}

	protected InitialDataLoad() {
		this(null, null, null);
	}

	@SuppressWarnings("unused")
	public void load(@Observes VRaptorInitialized initialized) {

		var type = new TypeToken<Map<String, Province>>(){}.getType();
		Map<String, Province> map = reader.read(PROVINCES_FILE, type);
		provincesRepository.bulkInsert(map);

		Properties properties = reader.read(PROPERTIES_FILE, Properties.class);
		propertiesRepository.bulkInsert(properties.getProperties());
	}
}