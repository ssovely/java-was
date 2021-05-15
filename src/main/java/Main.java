import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.HttpServer;
import domain.ServerConfig;
import service.JsonFileParser;
import service.ServerFactory;

public class Main {
	public static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
	public static final String VHOSTS_CONFIG_FILE_PATH = "vhosts.json";

	public static void main(String[] args) {
		try {
			List<ServerConfig> serverConfigs = new JsonFileParser().parseJsonFromFile(VHOSTS_CONFIG_FILE_PATH, ServerConfig.class);
			List<HttpServer> servers = ServerFactory.createHttpServer(serverConfigs);

			for (HttpServer server : servers) {
				server.start();
			}
		} catch (IOException exception) {
			LOGGER.error("Server could not start", exception);
		}
	}
}
