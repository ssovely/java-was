package domain;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utils.HttpRequestParser;

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
		try (InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8);
			 OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream())) {

			HttpRequest httpRequest = HttpRequestParser.parseRequest(inputStreamReader);
			VirtualHost virtualHost = findVirtualHost(httpRequest.getHost());
			HttpResponse httpResponse = virtualHost.execute(httpRequest);

			sendResponse(outputStreamWriter, httpRequest, httpResponse);

		} catch (IOException exception) {
			LOGGER.error("Error talking to {}", connection.getRemoteSocketAddress(), exception);
		}
	}

	private void sendResponse(OutputStreamWriter outputStreamWriter, HttpRequest request, HttpResponse response) throws
		IOException {
		response.setVersion(request.getVersion());
		outputStreamWriter.write(response.toString());
		outputStreamWriter.flush();
	}

	public VirtualHost findVirtualHost(String host) {
		String serverName = host.split(":")[0];
		return virtualHostMap.getOrDefault(serverName, virtualHosts.get(0));
	}
}
