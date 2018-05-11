package DAL.database;

import java.sql.Connection;
import java.sql.SQLException;

public interface IPostgreSqlCallback {
	void execute(Connection conn) throws SQLException;
}