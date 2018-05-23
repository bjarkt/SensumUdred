package DAL.database;

import ACQ.*;
import DAL.database.providers.DatabaseAdminProvider;
import DAL.database.providers.DatabaseDefaultProvider;
import DAL.database.providers.DatabaseSigningProvider;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicBoolean;

public class DatabaseService extends PostgreSqlDatabase implements IDatabaseService {
	private IDefaultService defaultService;
	private IAdminService adminService;
	private ISigningService signingService;

	public DatabaseService() {
		this.defaultService = new DatabaseDefaultProvider();
		this.adminService = new DatabaseAdminProvider();
		this.signingService = new DatabaseSigningProvider();
	}

	/**
	 * {@inheritDoc}
	 * Database service.
	 */
	@Override
	public IDefaultService getDefaultService() {
		return defaultService;
	}

	/**
	 * {@inheritDoc}
	 * Database service.
	 */
	@Override
	public IAdminService getAdminService() {
		return adminService;
	}

	/**
	 * {@inheritDoc}
	 * Database service.
	 */
	@Override
	public ISigningService getSigningService() {
		return signingService;
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
		PostgreSqlDatabase.getConnection();
	}


}