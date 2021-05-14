package domain;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VirtualHostRouter implements Runnable {
	public static final Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);
	private final List<VirtualHost> virtualHosts;
	private final Socket connection;

	public VirtualHostRouter(List<VirtualHost> virtualHosts, Socket connection) {
		this.virtualHosts = virtualHosts;
		this.connection = connection;
	}

	@Override
	public void run() {
		try (Reader reader = new InputStreamReader(new BufferedInputStream(connection.getInputStream()), StandardCharsets.UTF_8);
			 OutputStream outputStream = new BufferedOutputStream(connection.getOutputStream());
			 Writer writer = new OutputStreamWriter(outputStream)) {

			StringBuilder requestLine = new StringBuilder();
			while (reader.ready()) {
				int c = reader.read();
				// if (c == '\r' || c == '\n')
				// 	break;
				requestLine.append((char)c);
			}
			String get = requestLine.toString();

			LOGGER.info(get);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
