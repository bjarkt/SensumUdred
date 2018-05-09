package DAL.database;

import BLL.meeting.IMeeting;
import DAL.ConfigManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicBoolean;

public class PostgreSqlDatabase implements IDatabase {
	private static String url;
	private static String username;
	private static String password;

	static {
		url = ConfigManager.getInstance().getProperties().getProperty("db-url");
		username =  ConfigManager.getInstance().getProperties().getProperty("db-username");
		password =  ConfigManager.getInstance().getProperties().getProperty("db-password");
	}

	@Override
	public boolean fileMeeting(IMeeting meeting) {
		final AtomicBoolean isFiled = new AtomicBoolean(false);
		final String query = "";

		executeQuery(conn -> {
			try(PreparedStatement ps = conn.prepareStatement(query)) {
				//ps.set
				// set the parameters to the SQL...

				isFiled.set(ps.executeUpdate() > 0);
			} catch(SQLException e) {
				e.printStackTrace();
			}

		});

		return isFiled.get();
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
