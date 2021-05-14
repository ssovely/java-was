package domain;

import java.util.Map;

public class ErrorDocument {
	private final Map<String, String> errorDocument;

	public ErrorDocument(Map<String, String> errorDocument) {
		this.errorDocument = errorDocument;
	}
}
