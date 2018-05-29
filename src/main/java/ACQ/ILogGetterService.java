package ACQ;

import java.util.Set;

public interface ILogGetterService {

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
