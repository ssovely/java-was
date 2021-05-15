package domain;

public class VirtualHost {
	private final ServerName serverName;
	private final RootDirectory rootDirectory;
	private final ErrorDocument errorDocument;

	public VirtualHost(ServerConfig serverConfig) {
		this.serverName = new ServerName(serverConfig.getServerName());
		this.rootDirectory = new RootDirectory(serverConfig.getRootDirectory());
		this.errorDocument = new ErrorDocument(serverConfig.getErrorDocument());
	}

	public String getServerName() {
		return serverName.getName();
	}

	public HttpResponse execute(HttpRequest request) {
		return new HttpResponse();
	}
}
