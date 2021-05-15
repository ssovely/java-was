package utils;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import domain.HttpServer;
import domain.ServerConfig;

public class ServerFactoryTest {

	@Test
	public void createHttpServerTest() {
		//given
		List<ServerConfig> serverConfigs = new ArrayList<>();
		serverConfigs.add(new ServerConfig(8080, "www.jeonghun1.com", "/", null));
		serverConfigs.add(new ServerConfig(8080, "www.jeonghun2.com", "/", null));

		//when
		List<HttpServer> httpServers = ServerFactory.createHttpServer(serverConfigs);

		//then
		assertThat(httpServers).hasSize(1);
	}
}
