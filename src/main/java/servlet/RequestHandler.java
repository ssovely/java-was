package servlet;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import domain.HttpRequest;
import domain.HttpResponse;
import servlet.SimpleServlet;

public class RequestHandler {
	private final Map<String, SimpleServlet> servletMap;

	public RequestHandler(List<SimpleServlet> servlets) {
		this.servletMap = servlets.stream()
			.collect(Collectors.toMap(simpleServlet -> simpleServlet.getClass().getName(), Function.identity()));
	}

	public HttpResponse serviceServlet(HttpRequest request) {
		SimpleServlet simpleServlet = servletMap.get(request.getUri());

		return Optional.ofNullable(simpleServlet)
			.map(servlet -> servlet.service(request))
			.orElse(HttpResponse.EMPTY);
	}
}
