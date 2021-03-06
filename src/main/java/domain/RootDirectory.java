package domain;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

public class RootDirectory {
	private final File directory;

	public RootDirectory(String path) {
		validatePath(path);
		File directory = new File(path);
		validateExistsDirectory(directory);
		this.directory = directory;
	}

	private void validatePath(String path) {
		if (StringUtils.isBlank(path)) {
			throw new IllegalArgumentException("RootDirectoryPath must not be blank");
		}
	}

	private void validateExistsDirectory(File directory) {
		if (!directory.isDirectory()) {
			throw new IllegalArgumentException(directory + " does not exist as a directory");
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof RootDirectory))
			return false;
		RootDirectory that = (RootDirectory)o;
		return Objects.equals(directory, that.directory);
	}

	@Override
	public int hashCode() {
		return Objects.hash(directory);
	}

	public String getAbsolutePath(String uri) {
		String absolutePath = directory.getAbsolutePath();
		return absolutePath + FileSystems.getDefault().getSeparator() + uri;
	}

	public boolean isHigherLayer(String uri) {
		String targetPath = getAbsolutePath(uri);
		String absoluteTargetPath = new File(targetPath).getAbsolutePath();
		return !StringUtils.contains(absoluteTargetPath, directory.getAbsolutePath());
	}
}
