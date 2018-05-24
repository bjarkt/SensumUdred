import ACQ.IElucidation;
import ACQ.IElucidationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ElucidationTest {
	private IElucidationService service;

	public ElucidationTest() {
		this.service = TestHelper.getDatabaseService().getElucidationService();
	}

	@Test
	public void getElucidation() {
		IElucidation elucidation = service.getElucidation(1);

		Assertions.assertEquals("Dennis", elucidation.getCitizen().getFirstName());
	}
}