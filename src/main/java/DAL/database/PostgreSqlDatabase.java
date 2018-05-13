package DAL.database;

import BLL.ACQ.IMeeting;
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

		executeQuery(conn-> {
			PreparedStatement ps = conn.prepareStatement(query);
			//ps.set
			// set the parameters to the SQL...

			isFiled.set(ps.executeUpdate() > 0);
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

	public void createInquiryDB() {
		executeQuery(new IPostgreSqlCallback() {
			@Override
			public void execute(Connection connection) throws SQLException {
				connection.prepareStatement("CREATE TABLE SensumDB(\n" +
                        "\tCPR bigint(10),\n" +
                        "\tname varchar(500),\n" +
                        "\taddress varchar(500),\n" +
                        "\tgender varchar(500),\n" +
                        "\tcivilStatus varchar(500),\n" +
                        "\tregistrationDate varchar(500),\n" +
                        "\tdescription varchar(500),\n" +
                        "\tofferings varchar(500),\n" +
                        "\tinquirySource varchar(500),\n" +
                        "\tgrantings varchar(500),\n" +
                        "\tguardianship varchar(500),\n" +
                        "\tcontactDetails varchar(500),\n" +
                        "\tcitizenAgreement varchar(500),\n" +
                        "\tcitizinMunicipality varchar(500),\n" +
                        "\tspecialCircumstances varchar(500);\n" +
                        "\tPRIMARY KEY(CPR)\n" +
                        "\n" +
                        ");");
			}
		});
	}
}
