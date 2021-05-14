package domain;

import java.util.Map;
import java.util.Objects;

public class ServerConfig {
	private int serverPort = 80;
	private String serverName;
	private String rootDirectory;
	private Map<String, String> errorDocument;

	public ServerConfig() {
	}

	public ServerConfig(int serverPort, String serverName, String rootDirectory, Map<String, String> errorDocument) {
		this.serverPort = serverPort;
		this.serverName = serverName;
		this.rootDirectory = rootDirectory;
		this.errorDocument = errorDocument;
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

	public Map<String, String> getErrorDocument() {
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
