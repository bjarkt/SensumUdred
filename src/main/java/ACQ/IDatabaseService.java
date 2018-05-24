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
	 * Tests the connection to the database.
	 * @throws SQLException if any error occurs
	 */
	void testConnection() throws SQLException;
}
