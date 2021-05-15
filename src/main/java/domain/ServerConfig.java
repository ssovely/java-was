package domain;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import utils.JsonFileParser;

public class ServerConfig {
	private static final String VHOSTS_CONFIG_FILE_NAME = "vhosts.json";
	private int serverPort = 80;
	private String serverName;
	private String rootDirectory;
	private Map<Integer, String> errorDocument;

	public ServerConfig() {
	}

	public ServerConfig(int serverPort, String serverName, String rootDirectory, Map<Integer, String> errorDocument) {
		this.serverPort = serverPort;
		this.serverName = serverName;
		this.rootDirectory = rootDirectory;
		this.errorDocument = errorDocument;
	}

	public static List<ServerConfig> loadServerConfig() throws IOException {
		return new JsonFileParser().parseJsonFromFile(VHOSTS_CONFIG_FILE_NAME, ServerConfig.class);
	}

	public int getServerPort() {
		return serverPort;
	}

	public String getServerName() {
		return serverName;
	}

	public String getRootDirectory() {
		return rootDirectory;
	}

	public Map<Integer, String> getErrorDocument() {
		return errorDocument;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ServerConfig))
			return false;
		ServerConfig that = (ServerConfig)o;
		return serverPort == that.serverPort && Objects.equals(serverName, that.serverName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(serverPort, serverName);
	}
}
