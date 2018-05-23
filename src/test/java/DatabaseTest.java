import DAL.database.DatabaseService;
import ACQ.IDatabaseService;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatabaseTest {
	private IDatabaseService dbService;

	public DatabaseTest() {
		this.dbService = new DatabaseService();
	}

	@Test
	public void connectionTest() {
		boolean isValid = true;

		try {
			dbService.testConnection();
		} catch(SQLException e) {
			isValid = false;
		}

		assert isValid;
	}

	@Test
	public void signInAndOut() {
		assertEquals("tester", dbService.getSigningService().signIn("tester", "tester").getAccount().getUsername());
		assert dbService.getSigningService().signOut("tester");
	}

	@Test
	public void userExist() {
		assert dbService.getDefaultService().userExists("99999999");
	}

	@Test
	public void accountExist() {
		assert dbService.getDefaultService().accountExists("tester");
	}

	@Test
	public void lockAndUnlockAccount() {
		assert dbService.getAdminService().lockAccount("tester");
		assert dbService.getAdminService().unlockAccount("tester");
	}

	@Test
	public void getAllUsersAndAccounts() {
		assertEquals(2, dbService.getDefaultService().getAllUsers(2).size());
		assertEquals(2, dbService.getDefaultService().getAllAccounts(2).size());
	}
}