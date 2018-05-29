package ACQ;

import java.util.Date;

public interface IEventLog extends Comparable<IEventLog> {
    /**
     * ID of the event
     * @return long id
     */
    long getId();

    /**
     * What method was called
     * @return name of method
     */
    String getMethodName();

    /**
     * Description of what happened
     * @return description
     */
    String getDescription();

    /**
     * What logging level was used
     * @return log level
     */
    LogLevel getLogLevel();

    /**
     * What action was done
     * @return log action
     */
    LogAction getLogAction();

    /**
     * When did the action happen?
     * @return date of log
     */
    Date getDateTime();
}
