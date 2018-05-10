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

	public void createInquiryDB() {
		executeQuery(new IPostgreSqlCallback() {
			@Override
			public void execute(Connection connection) throws SQLException {
				connection.prepareStatement("CREATE TABLE SensumDB(\n" +
                        "\tCPR bigint(10),\n" +
                        "\tName varchar(500),\n" +
                        "\tAddress varchar(500),\n" +
                        "\tGender varchar(500),\n" +
                        "\tCivilStatus varchar(500),\n" +
                        "\tRegistrationDate varchar(500),\n" +
                        "\tinquiryDescription varchar(500),\n" +
                        "\tOfferings varchar(500),\n" +
                        "\tinquirySource varchar(500),\n" +
                        "\tGrantings varchar(500),\n" +
                        "\tGuardianship varchar(500),\n" +
                        "\tcontactDetails varchar(500),\n" +
                        "\tCitizenAgreement varchar(500),\n" +
                        "\tcitizinMunicipality varchar(500),\n" +
                        "\tspecialCircumstances varchar(500);\n" +
                        "\tPRIMARY KEY(CPR)\n" +
                        "\n" +
                        ");");
			}
		});
	}
}
