package DAL.database;

import DAL.ConfigManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Connects to the database by calling a method, that gives access to the connection.
 * Subclasses can use this method and execute their own content.
 */
public abstract class PostgreSqlDatabase {
	private static String url;
	private static String username;
	private static String password;

	static {
		url = ConfigManager.getInstance().getProperties().getProperty("db-url");
		username =  ConfigManager.getInstance().getProperties().getProperty("db-username");
		password =  ConfigManager.getInstance().getProperties().getProperty("db-password");
	}

	/**
	 * Executes whatever is inside the callback.
	 * The callback gives access to a {@link Connection}.
	 * @param callback any callback
	 */
	protected void executeQuery(IPostgreSqlCallback callback) {
		try(Connection conn = getConnection()) {
			conn.setAutoCommit(false);

			try {
				callback.execute(conn);
				conn.commit();
			} catch(SQLException e) {
				conn.rollback();
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get a connection to the database.
	 * @return a connection
	 * @throws SQLException if a sql error happens
	 */
	static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}
}