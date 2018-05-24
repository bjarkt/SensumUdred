package DAL.database;

import ACQ.*;
import DAL.database.providers.DatabaseAdminProvider;
import DAL.database.providers.DatabaseDefaultProvider;
import DAL.database.providers.DatabaseElucidationProvider;
import DAL.database.providers.DatabaseSigningProvider;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Contains all the different providers in the database section.
 * It can invoke all methods the different services provide.
 */
public class DatabaseService extends PostgreSqlDatabase implements IDatabaseService {
	private IDefaultService defaultService;
	private IAdminService adminService;
	private ISigningService signingService;
	private IElucidationService elucidationService;

	public DatabaseService() {
		this.defaultService = new DatabaseDefaultProvider();
		this.adminService = new DatabaseAdminProvider();
		this.signingService = new DatabaseSigningProvider();
		this.elucidationService = new DatabaseElucidationProvider();
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

	/**
	 * {@inheritDoc}
	 * Database service.
	 */
	@Override
	public IElucidationService getElucidationService() {
		return elucidationService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void testConnection() throws SQLException {
		PostgreSqlDatabase.getConnection();
	}
}