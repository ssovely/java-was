package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class PortTest {
	@Test
	public void constructTest() {
		Port port = new Port(0);

		assertThat(port).isNotNull();
	}

	@Test
	public void constructTest_LessThanZero() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> {
				new Port(-1);
			});
	}

	@Test
	public void constructTest_GreaterThanZero() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> {
				new Port(70000);
			});
	}
}
