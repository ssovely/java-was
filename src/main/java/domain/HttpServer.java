package domain;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpServer {
	public static final Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);
	private static final int MAX_THREAD = 50;
	private final Port port;
	private final List<VirtualHost> virtualHosts;

	public HttpServer(int port, List<VirtualHost> virtualHosts) {
		this.port = new Port(port);
		this.virtualHosts = virtualHosts;
	}

	public void start() throws IOException {
		ExecutorService pool = Executors.newFixedThreadPool(MAX_THREAD);
		try (ServerSocket server = new ServerSocket(port.getValue())) {
			while (true) {
				try {
					Socket request = server.accept();
					//find VirtualHost
				} catch (IOException ex) {
					LOGGER.warn("Error accepting connection", ex);
				}
			}
		}
	}

	public List<VirtualHost> getVirtualHosts() {
		return virtualHosts;
	}
}
