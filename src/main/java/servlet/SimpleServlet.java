package servlet;

import domain.HttpRequest;
import domain.HttpResponse;

public interface SimpleServlet {
	HttpResponse service(HttpRequest request);
}
