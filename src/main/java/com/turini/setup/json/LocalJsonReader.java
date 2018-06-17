package com.turini.setup.json;

import com.google.gson.Gson;

import javax.enterprise.context.Dependent;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;

import static com.google.common.base.Strings.nullToEmpty;
import static java.nio.file.Paths.get;

@Dependent
public class LocalJsonReader implements JsonReader {

	@Override
	public <T> T read(String fileName, Type type) {

		fileName = nullToEmpty(fileName).trim();
		if (fileName.isEmpty()) {
			throw new IllegalArgumentException("fileName is required");
		}
		try(var reader = newBufferedReader(fileName)) {
			return new Gson().fromJson(reader, type);
		} catch (Exception e) {
			throw new RuntimeException("Couldn't read JSON file", e);
		}
	}

	private BufferedReader newBufferedReader(String fileName)
			throws IOException {
		var classLoader = getClass().getClassLoader();
		return Files.newBufferedReader(
			get(classLoader.getResource(fileName).getPath())
		);
	}
}