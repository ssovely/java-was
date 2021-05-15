package domain;

import java.util.Objects;

public class Port {
	public static final int MIN_PORT = 0;
	public static final int MAX_PORT = 65535;

	private final int port;

	public Port(int port) {
		validatePortRange(port);
		this.port = port;
	}

	private void validatePortRange(int port) {
		if (port < MIN_PORT || port > MAX_PORT) {
			throw new IllegalArgumentException(String.format("Ports must be between %d and %d", MIN_PORT, MAX_PORT));
		}
	}

	public int getValue() {
		return port;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Port))
			return false;
		Port port1 = (Port)o;
		return port == port1.port;
	}

	@Override
	public int hashCode() {
		return Objects.hash(port);
	}
}
