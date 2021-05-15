package domain;

import java.util.Map;

import type.HttpStatus;

public class HttpResponse {
	public static final HttpResponse EMPTY = new HttpResponse();
	private String version;
	private HttpStatus status;
	private Map<String, String> header;
	private String body;

	public HttpResponse() {
	}

	public HttpResponse(HttpStatus status) {
		this(status, null);
	}

	public HttpResponse(HttpStatus status, String body) {
		this.status = status;
		this.body = body;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public boolean isEmpty(){
		return this == EMPTY;
	}

	@Override
	public String toString() {
		return version + " " + status.toString() + System.lineSeparator()
			+ System.lineSeparator()
			+ body;
	}
}
