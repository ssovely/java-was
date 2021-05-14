package service;

import static org.assertj.core.api.Assertions.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import domain.ServerConfig;

public class JsonFileParserTest {
	private JsonFileParser jsonFileParser = new JsonFileParser();

	@Test
	public void parseJsonFormFile() throws IOException {
		List<ServerConfig> serverConfigs = jsonFileParser.parseJsonFromFile("vhosts.json", ServerConfig.class);

		assertThat(serverConfigs).hasSize(2)
			.containsExactly(
				new ServerConfig(80, "www.jeonghun1.com", "/", null),
				new ServerConfig(80, "www.jeonghun2.com", "/", null)
			);
	}
}