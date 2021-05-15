package domain;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import type.HttpStatus;

public class ErrorDocument {
	public static final Logger LOGGER = LoggerFactory.getLogger(ErrorDocument.class);
	private final Map<HttpStatus, String> errorDocument;

	public ErrorDocument(Map<Integer, String> inputErrorDocument) {
		this.errorDocument = map(inputErrorDocument);
	}

	private Map<HttpStatus, String> map(Map<Integer, String> inputErrorDocument) {
		if (Objects.isNull(inputErrorDocument)) {
			return new HashMap<>();
		}

		Map<HttpStatus, String> errorDocument = new HashMap<>();
		inputErrorDocument.forEach((code, path) -> errorDocument.put(HttpStatus.valueOfCode(code), path));
		return errorDocument;
	}

	public void checkHttpStatus(HttpResponse response) {
		String path = errorDocument.get(response.getStatus());

		if (Objects.isNull(path)) {
			return;
		}

		try {
			String projectPath = ErrorDocument.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			File file = new File(projectPath + path);
			String body = Files.lines(file.toPath())
				.collect(Collectors.joining(System.lineSeparator()));
			response.setBody(body);
		} catch (IOException exception) {
			LOGGER.error("에러페이지 load 실패", exception);
		}
	}
}
