package DAL.database;

import BLL.meeting.IMeeting;

import java.sql.SQLException;

public interface IDatabase {

	/**
	 * Tests the connection to the database.
	 * @throws SQLException if any error occurs
	 */
	void testConnection() throws SQLException;

	/**
	 * Files a meeting into the database.
	 * @param meeting any meeting
	 * @return successful or not
	 */
	boolean fileMeeting(IMeeting meeting);
}
