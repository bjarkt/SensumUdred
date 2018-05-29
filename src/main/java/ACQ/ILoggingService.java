package ACQ;

import java.util.Set;

public interface ILoggingService {


	/**
	 * Logs the event/method.
	 * @param methodName name of the method.
	 * @param description a description of the method
	 * @param logLevel the level of the method
	 * @param logAction the action of the method
	 * @return -1 if it could not insert; otherwise the id of the log
	 */
	long logEvent(String methodName, String description, LogLevel logLevel, LogAction logAction);

	/**
	 * Adds a log to it can be received by searched by the account id.
	 * It must contain either a changelog id or a eventlog id.
	 * @param accountId Id of the account
	 * @param sessionId Id of the session (WIP)
	 * @param changelogId Id of the changelog
	 * @param eventLogId Id of the eventlog
	 * @return true, if successful; otherwise false
	 */
	boolean addToLogs(long accountId, long sessionId, long changelogId, long eventLogId);

	/**
	 * Get all event logs
	 * @return Sorted set of event logs
	 */
	Set<IEventLog> getEventLogs();

	/**
	 * Get event log for SSN
	 * @param ssn User's ssn
	 */
	void getEventLogForSSN(String ssn);
}