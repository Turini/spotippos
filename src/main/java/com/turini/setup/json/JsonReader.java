package com.turini.setup.json;

import java.lang.reflect.Type;

public interface JsonReader {

	<T> T read(String fileName, Type type);
}
