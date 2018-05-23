package DAL.database;

import ACQ.IAdminService;
import ACQ.IDatabaseService;
import ACQ.IMeeting;
import ACQ.ISigningService;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicBoolean;

public class DatabaseService extends PostgreSqlDatabase implements IDatabaseService {
	private IAdminService adminService;
	private ISigningService signingService;

	public DatabaseService() {
		adminService = new DatabaseAdminProvider();
		signingService = new DatabaseSigningProvider();
	}

	@Override
	public IAdminService getAdminService() {
		return adminService;
	}

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