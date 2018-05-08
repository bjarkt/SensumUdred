package DAL.database;

import java.sql.Connection;

public interface IPostgreSqlCallback {
	void execute(Connection conn);
}