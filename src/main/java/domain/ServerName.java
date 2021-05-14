package domain;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

public class ServerName {
	private final String name;

	public ServerName(String name) {
		validateName(name);
		this.name = name;
	}

	private void validateName(String name) {
		if (StringUtils.isBlank(name)) {
			throw new IllegalArgumentException("ServerName must not be blank");
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ServerName))
			return false;
		ServerName that = (ServerName)o;
		return Objects.equals(name, that.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
