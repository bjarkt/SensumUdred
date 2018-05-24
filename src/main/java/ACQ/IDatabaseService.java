package ACQ;

import java.sql.SQLException;

public interface IDatabaseService {
	/**
	 * Get the default service.
	 * @return default service interface
	 */
	IDefaultService getDefaultService();

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
	 * Get the elucidation service.
	 * @return elucidation service interface
	 */
	IElucidationService getElucidationService();

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
