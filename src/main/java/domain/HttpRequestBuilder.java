package domain;

import java.util.HashMap;
import java.util.Map;

public class HttpRequestBuilder {
	private String method;
	private String uri;
	private String version;
	private Map<String, String> header = new HashMap<>();

	public HttpRequestBuilder() {
	}

	public HttpRequestBuilder method(String method) {
		this.method = method;
		return this;
	}

	public HttpRequestBuilder uri(String uri) {
		this.uri = uri;
		return this;
	}

	public HttpRequestBuilder version(String version) {
		this.version = version;
		return this;
	}

	public HttpRequestBuilder putHeader(String key, String value) {
		this.header.put(key, value);
		return this;
	}

	public HttpRequest build() {
		return new HttpRequest(method, uri, version, header);
	}
}
