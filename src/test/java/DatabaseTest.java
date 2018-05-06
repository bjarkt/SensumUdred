import DAL.database.IDatabase;
import DAL.database.PostgreSqlDatabase;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class DatabaseTest {
	@Test
	public void databaseConnectionTest() {
		IDatabase db = new PostgreSqlDatabase();

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