package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

public class JsonFileParser {
	private static final ObjectMapper objectMapper = new ObjectMapper();

	public <T> List<T> parseJsonFromFile(String filePath, Class<T> clazz) throws IOException {
		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath)) {
			CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, clazz);

			return objectMapper.readValue(inputStream, collectionType);
		}
	}
}
