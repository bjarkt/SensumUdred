package DAL.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSqlDatabase implements IDatabase {
	private static String url;
	private static String username;
	private static String password;

	static {
		url = "jdbc:postgresql://horton.elephantsql.com:5432/cxiasneu";
		username = "cxiasneu";
		password = "OY2shAU8fq2NQXMpbxU21AFNmOczgUkF";
	}

	@Override
	public void testConnection() throws SQLException {
		getConnection();
	}

	protected void executeQuery(IPostgreSqlCallback callback) {
		try(Connection conn = getConnection()) {
			callback.execute(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}
}
