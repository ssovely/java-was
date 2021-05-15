package filter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import domain.HttpRequest;
import domain.HttpResponse;
import type.HttpStatus;

public class SecurityFilterFactory {
	private final List<SecurityFilter> securityFilters = new ArrayList<>();

	public void addFilter(Predicate<HttpRequest> predicate, HttpStatus status) {
		securityFilters.add(new SecurityFilter(predicate, status));
	}

	public HttpResponse filter(HttpRequest request) {
		return securityFilters.stream()
			.filter(securityFilter -> securityFilter.filter(request))
			.findFirst()
			.map(securityFilters -> new HttpResponse(securityFilters.getStatus()))
			.orElse(HttpResponse.EMPTY);
	}

}
