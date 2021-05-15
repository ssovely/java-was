package type;

import java.util.Arrays;

public enum HttpStatus {
	OK(200, "OK"),
	BAD_REQUEST(400, "Bad Request"),
	FORBIDDEN(403, "Forbidden"),
	NOT_FOUND(404, "Not Found"),
	INTERNAL_SERVER_ERROR(500, "Internal Server Error");

	private final int code;
	private final String text;

	HttpStatus(int code, String text) {
		this.code = code;
		this.text = text;
	}

	public static HttpStatus valueOfCode(int code) {
		return Arrays.stream(values())
			.filter(httpStatus -> httpStatus.code == code)
			.findFirst()
			.orElseThrow(IllegalArgumentException::new);
	}

	@Override
	public String toString() {
		return code + " " + text;
	}
}
