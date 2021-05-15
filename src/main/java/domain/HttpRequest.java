package domain;

import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import type.HttpMethod;

public class HttpRequest {
	public static final String HOST = "Host";
	private HttpMethod method;
	private String uri;
	private String version;
	private Map<String, String> header;

	public HttpRequest() {
	}

	public HttpRequest(String method, String uri, String version, Map<String, String> header) {
		validateHttpRequest(method, uri, version, header);
		this.method = HttpMethod.valueOf(method);
		this.uri = uri;
		this.version = version;
		this.header = header;
	}

	private void validateHttpRequest(String method, String uri, String version, Map<String, String> header) {
		if (StringUtils.isAnyBlank(method, uri, version)) {
			throw new IllegalArgumentException();
		}

		if (Objects.isNull(header)
			|| StringUtils.isBlank(header.get(HOST))) {
			throw new IllegalArgumentException();
		}
	}

	public static HttpRequestBuilder builder() {
		return new HttpRequestBuilder();
	}

	public boolean isMethod(HttpMethod method) {
		return this.method == method;
	}

	public String getVersion() {
		return version;
	}

	public boolean isSameUriExtension(String extension) {
		return StringUtils.equals(StringUtils.substringAfter(uri, "."), extension);
	}

	public String getUri() {
		return uri.substring(1);
	}

	public String getHost() {
		return header.get(HOST);
	}
}
