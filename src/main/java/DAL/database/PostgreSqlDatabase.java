package DAL.database;

import DAL.ConfigManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class PostgreSqlDatabase {
	private static String url;
	private static String username;
	private static String password;

	static {
		url = ConfigManager.getInstance().getProperties().getProperty("db-url");
		username =  ConfigManager.getInstance().getProperties().getProperty("db-username");
		password =  ConfigManager.getInstance().getProperties().getProperty("db-password");
	}

	protected void executeQuery(IPostgreSqlCallback callback) {
		try(Connection conn = getConnection()) {
			conn.setAutoCommit(false);

			try {
				callback.execute(conn);
				conn.commit();
			} catch(SQLException e) {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}
}