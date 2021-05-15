package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class ServerNameTest {
	@Test
	public void constructTest() {
		ServerName serverName = new ServerName("www.jeonghun.com");

		assertThat(serverName).isNotNull();
	}

	@Test
	public void constructTest_BlankString() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> {
				new ServerName("");
			});
	}
}
