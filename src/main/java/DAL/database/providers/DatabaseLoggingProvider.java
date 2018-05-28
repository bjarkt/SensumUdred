package DAL.database.providers;

import ACQ.ILoggingService;
import ACQ.LogAction;
import ACQ.LogLevel;
import DAL.database.PostgreSqlDatabase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class DatabaseLoggingProvider extends PostgreSqlDatabase implements ILoggingService {
	/**
	 * {@inheritDoc}
	 */
	@Override
	public long logEvent(String methodName, String description, LogLevel logLevel, LogAction logAction) {
		AtomicLong atomicLong = new AtomicLong(-1);

		executeQuery(conn -> {
			String query = "INSERT INTO eventlogs(id, method_name, description, loglevel, logaction, datetime)" +
					" VALUES (DEFAULT, ?, ?, ?, ?, now());";
			PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, methodName);
			ps.setString(2, description);
			ps.setInt(3, logLevel.ordinal());
			ps.setInt(4, logAction.ordinal());

			if(ps.executeUpdate() == 1) {
				ResultSet rs = ps.getGeneratedKeys();

				if(rs.next()) {
					atomicLong.set(rs.getLong(1));
				}
			}
		});

		return atomicLong.get();
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean addToLogs(long accountId, long sessionId, long changelogId, long eventLogId) {
		AtomicBoolean bool = new AtomicBoolean(false);

		if(changelogId > 0 ^ eventLogId > 0) {
			executeQuery(conn -> {
				String query = "INSERT INTO logs(accounts_id, sessionid, changelogs_id, eventlogs_id) VALUES (?, ?, ?, ?);";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setLong(1, accountId);
				ps.setLong(2, sessionId);
				ps.setLong(3, changelogId);
				ps.setLong(4, eventLogId);

				bool.set(ps.executeUpdate() == 1);
			});
		}

		return bool.get();
	}
}