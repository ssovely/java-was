package service;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.HttpRequest;
import domain.HttpResponse;
import domain.VirtualHost;

public class VirtualHostExecutor implements Runnable {
	public static final Logger LOGGER = LoggerFactory.getLogger(VirtualHostExecutor.class);
	private final Map<String, VirtualHost> virtualHostMap;
	private final List<VirtualHost> virtualHosts;
	private final Socket connection;

	public VirtualHostExecutor(List<VirtualHost> virtualHosts, Socket connection) {
		this.virtualHosts = virtualHosts;
		this.connection = connection;
		this.virtualHostMap = virtualHosts.stream()
			.collect(
				Collectors.toMap(VirtualHost::getServerName, Function.identity())
			);
	}

	@Override
	public void run() {
		try (OutputStream outputStream = new BufferedOutputStream(connection.getOutputStream());
			 Writer writer = new OutputStreamWriter(outputStream)) {

			HttpRequest httpRequest = HttpRequestParser.parseRequest(connection.getInputStream());
			VirtualHost virtualHost = findVirtualHost(httpRequest.getHost());
			HttpResponse httpResponse = virtualHost.execute(httpRequest);

		} catch (IOException exception) {
			LOGGER.error("Error talking to {}", connection.getRemoteSocketAddress(), exception);
		}
	}

	public VirtualHost findVirtualHost(String serverName) {
		return virtualHostMap.getOrDefault(serverName, virtualHosts.get(0));
	}
}
