package servlet;

import domain.HttpRequest;
import domain.HttpResponse;
import type.HttpStatus;

public class Hello implements SimpleServlet {
	@Override
	public HttpResponse service(HttpRequest request) {
		return new HttpResponse(HttpStatus.OK, "Hello");
	}
}
