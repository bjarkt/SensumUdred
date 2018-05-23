package ACQ;

import java.sql.SQLException;

public interface IDatabaseService {
	/**
	 * Get the admin service.
	 * @return admin service interface
	 */
	IAdminService getAdminService();

	/**
	 * Get the signing service.
	 * @return signing service interface
	 */
	ISigningService getSigningService();

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
