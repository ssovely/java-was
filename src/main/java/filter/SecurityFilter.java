package filter;

import java.util.function.Predicate;

import domain.HttpRequest;
import type.HttpStatus;

public class SecurityFilter {
	private final Predicate<HttpRequest> httpRequestPredicate;
	private final HttpStatus filteredStatus;

	public SecurityFilter(Predicate<HttpRequest> httpRequestPredicate, HttpStatus filteredStatus) {
		this.httpRequestPredicate = httpRequestPredicate;
		this.filteredStatus = filteredStatus;
	}

	public boolean filter(HttpRequest request) {
		return httpRequestPredicate.test(request);
	}

	public HttpStatus getStatus() {
		return filteredStatus;
	}
}
