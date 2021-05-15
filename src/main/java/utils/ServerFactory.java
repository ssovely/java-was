package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domain.HttpServer;
import domain.ServerConfig;
import domain.VirtualHost;

public class ServerFactory {
	public static List<HttpServer> createHttpServer(List<ServerConfig> serverConfigs) {
		Map<Integer, List<ServerConfig>> groupedMap = groupingByPort(serverConfigs);

		List<HttpServer> httpServers = new ArrayList<>();
		groupedMap.forEach((port, groupedServerConfigs) -> {
			List<VirtualHost> virtualHosts = map(groupedServerConfigs);
			httpServers.add(new HttpServer(port, virtualHosts));
		});

		return httpServers;
	}

	private static Map<Integer, List<ServerConfig>> groupingByPort(List<ServerConfig> serverConfigs) {
		return serverConfigs.stream()
			.collect(
				Collectors.groupingBy(ServerConfig::getServerPort)
			);
	}

	private static List<VirtualHost> map(List<ServerConfig> serverConfigs) {
		return serverConfigs.stream()
			.map(VirtualHost::new)
			.collect(Collectors.toList());
	}
}
