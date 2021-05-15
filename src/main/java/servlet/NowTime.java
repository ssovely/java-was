package servlet;

import java.time.LocalDateTime;

import domain.HttpRequest;
import domain.HttpResponse;
import type.HttpStatus;

public class NowTime implements SimpleServlet {
	@Override
	public HttpResponse service(HttpRequest request) {
		return new HttpResponse(HttpStatus.OK, LocalDateTime.now().toString());
	}
}
