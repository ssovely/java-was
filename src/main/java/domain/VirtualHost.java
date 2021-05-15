package domain;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import filter.SecurityFilterFactory;
import servlet.Hello;
import servlet.NowTime;
import servlet.RequestHandler;
import servlet.SimpleServlet;
import type.HttpMethod;
import type.HttpStatus;

public class VirtualHost {
	private final ServerName serverName;
	private final RootDirectory rootDirectory;
	private final ErrorDocument errorDocument;
	private final SecurityFilterFactory securityFilterFactory;
	private final RequestHandler requestHandler;

	public VirtualHost(ServerConfig serverConfig) {
		this.serverName = new ServerName(serverConfig.getServerName());
		this.rootDirectory = new RootDirectory(serverConfig.getRootDirectory());
		this.errorDocument = new ErrorDocument(serverConfig.getErrorDocument());
		this.securityFilterFactory = new SecurityFilterFactory();
		setSecurityFilterFactory();
		this.requestHandler = new RequestHandler(addedServlets());
	}

	private void setSecurityFilterFactory() {
		securityFilterFactory.addFilter(request -> rootDirectory.isHigherLayer(request.getUri()), HttpStatus.FORBIDDEN);
		securityFilterFactory.addFilter(request -> request.isSameUriExtension("exe"), HttpStatus.FORBIDDEN);
	}

	private List<SimpleServlet> addedServlets() {
		List<SimpleServlet> servlets = new ArrayList<>();
		servlets.add(new Hello());
		servlets.add(new NowTime());

		return servlets;
	}

	public String getServerName() {
		return serverName.getName();
	}

	public HttpResponse execute(HttpRequest request) throws IOException {
		HttpResponse response = processRequest(request);

		errorDocument.checkHttpStatus(response);
		return response;
	}

	private HttpResponse processRequest(HttpRequest request) throws IOException {
		HttpResponse response = securityFilterFactory.filter(request);
		if (!response.isEmpty()) {
			return response;
		}

		if (request.isMethod(HttpMethod.GET)) {
			response = processStaticResource(request.getUri());

			if (response.isEmpty()) {
				response = requestHandler.serviceServlet(request);
			}

			if (response.isEmpty()) {
				response = new HttpResponse(HttpStatus.NOT_FOUND);
			}

			return response;
		} else {
			return new HttpResponse(HttpStatus.BAD_REQUEST);
		}
	}

	private HttpResponse processStaticResource(String uri) throws IOException {
		String absolutePath = rootDirectory.getAbsolutePath(uri);
		File file = new File(absolutePath);

		if (!file.exists()) {
			return HttpResponse.EMPTY;
		}

		String body = Files.lines(file.toPath())
			.collect(Collectors.joining(System.lineSeparator()));

		return new HttpResponse(HttpStatus.OK, body);
	}

}
