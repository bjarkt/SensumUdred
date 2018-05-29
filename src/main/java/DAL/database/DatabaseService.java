package DAL.database;

import ACQ.*;
import DAL.database.providers.*;

import java.sql.SQLException;

/**
 * Contains all the different providers in the database section.
 * It can invoke all methods the different services provide.
 */
public class DatabaseService extends PostgreSqlDatabase implements IDatabaseService {
	private IDefaultService defaultService;
	private IAdminService adminService;
	private ISigningService signingService;
	private IElucidationService elucidationService;
	private ILoggingService loggingService;

	public DatabaseService() {
		this.defaultService = new DatabaseDefaultProvider();
		this.adminService = new DatabaseAdminProvider();
		this.signingService = new DatabaseSigningProvider();
		this.elucidationService = new DatabaseElucidationProvider();
		this.loggingService = new DatabaseLoggingProvider();
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
	 * Database service.
	 */
	@Override
	public ILoggingService getLoggingService() {
		return loggingService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void testConnection() throws SQLException {
		PostgreSqlDatabase.getConnection();
	}
}