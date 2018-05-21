import DAL.database.DatabaseService;
import DAL.database.IDatabaseService;
import DAL.database.PostgreSqlDatabase;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class DatabaseTest {
	@Test
	public void databaseConnectionTest() {
		IDatabaseService db = new DatabaseService();

		boolean isValid = true;
		try {
			db.testConnection();
		} catch(SQLException e) {
			isValid = false;
			e.printStackTrace();
		}

		assert isValid;
	}
}