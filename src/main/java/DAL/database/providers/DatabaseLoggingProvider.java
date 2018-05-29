package DAL.database.providers;

import ACQ.IEventLog;
import ACQ.ILoggingService;
import ACQ.LogAction;
import ACQ.LogLevel;
import DAL.database.PostgreSqlDatabase;
import DAL.dataobject.EventLog;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicBoolean;
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

	@Override
	public Set<IEventLog> getEventLogs() {
		LogLevel[] logLevels = LogLevel.values();
		LogAction[] logActions = LogAction.values();
		Set<IEventLog> eventLogs = new TreeSet<>();

		executeQuery(conn -> {
			String query = "SELECT id, method_name, description, loglevel, logaction, datetime FROM eventlogs";
			PreparedStatement ps = conn.prepareStatement(query);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				long id = rs.getLong("id");
				String methodName = rs.getString("method_name");
				String description = rs.getString("description");
				LogLevel logLevel = logLevels[rs.getInt("loglevel")];
				LogAction logAction = logActions[rs.getInt("logaction")];
				Date dateTime = rs.getTimestamp("datetime");
				eventLogs.add(new EventLog(id, methodName, description, logLevel, logAction, dateTime));
			}
		});

		return eventLogs;
	}

	@Override
	public void getEventLogForSSN(String ssn) {
		throw new NotImplementedException();
	}
}