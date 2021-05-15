package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.lang3.StringUtils;

import domain.HttpRequest;
import domain.HttpRequestBuilder;

public class HttpRequestParser {
	public static HttpRequest parseRequest(InputStreamReader inputStreamReader) throws IOException {
		BufferedReader reader = new BufferedReader(inputStreamReader);

		if (!reader.ready()) {
			throw new IllegalStateException();
		}

		HttpRequestBuilder httpRequestBuilder = HttpRequest.builder();
		setRequestLine(httpRequestBuilder, reader.readLine());

		while (reader.ready()) {
			putHeader(httpRequestBuilder, reader.readLine());
		}

		return httpRequestBuilder.build();
	}

	public static void setRequestLine(HttpRequestBuilder builder, String line) {
		if (StringUtils.isBlank(line)) {
			throw new IllegalArgumentException();
		}

		String[] tokens = line.split("\\s+");

		builder.method(tokens[0])
			.uri(tokens[1])
			.version(tokens[2]);
	}

	public static void putHeader(HttpRequestBuilder builder, String line) {
		if (StringUtils.isBlank(line)) {
			return;
		}

		String[] tokens = line.split(":");
		builder.putHeader(tokens[0].trim(), tokens[1].trim());
	}
}
