import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.ServerConfig;
import service.JsonFileParser;

public class Main {
	public static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
	public static final String VHOSTS_CONFIG_FILE_PATH = "vhosts.json";

	public static void main(String[] args) throws IOException {
		List<ServerConfig> serverConfigs = new JsonFileParser().parseJsonFormFile(VHOSTS_CONFIG_FILE_PATH, ServerConfig.class);
	}
}
