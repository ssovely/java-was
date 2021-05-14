package domain;

public class VirtualHost {
	private final ServerName name;
	private final RootDirectory rootDirectory;
	private final ErrorDocument errorDocument;

	public VirtualHost(ServerConfig serverConfig) {
		this.name = new ServerName(serverConfig.getServerName());
		this.rootDirectory = new RootDirectory(serverConfig.getRootDirectory());
		this.errorDocument = new ErrorDocument(serverConfig.getErrorDocument());
	}
}
