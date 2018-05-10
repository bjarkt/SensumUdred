package DAL.database;

import java.sql.Connection;
import java.sql.SQLException;

public interface IPostgreSqlCallback {
	void execute(Connection connection) throws SQLException;
}