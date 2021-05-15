package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class RootDirectoryTest {
	@Test
	public void constructTest() {
		RootDirectory rootDirectory = new RootDirectory("./logs");

		assertThat(rootDirectory).isNotNull();
	}

	@Test
	public void constructTest_BlankString() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> {
				new RootDirectory("");
			});
	}

	@Test
	public void constructTest_NotExistsDirectory() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> {
				new RootDirectory("./abcdef");
			});
	}
}
