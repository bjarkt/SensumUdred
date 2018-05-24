import ACQ.IElucidationService;
import org.junit.jupiter.api.Test;

public class ElucidationTest {
	private IElucidationService service;

	public ElucidationTest() {
		this.service = TestHelper.getDatabaseService().getElucidationService();
	}

	@Test
	public void getElucidation() {

	}
}