package DAL.database;

import ACQ.IUserService;
import ACQ.IMeeting;
import ACQ.ISigningService;

import java.sql.SQLException;

public interface IDatabaseService extends ISigningService, IUserService {
	/**
	 * Files a meeting into the database.
	 * @param meeting any meeting
	 * @return successful or not
	 */
	boolean fileMeeting(IMeeting meeting);

	/**
	 * Tests the connection to the database.
	 * @throws SQLException if any error occurs
	 */
	void testConnection() throws SQLException;
}
