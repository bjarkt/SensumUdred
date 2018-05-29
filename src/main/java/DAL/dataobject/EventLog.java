package DAL.dataobject;

import ACQ.IEventLog;
import ACQ.LogAction;
import ACQ.LogLevel;

import java.util.Date;

public class EventLog implements IEventLog {
    private long id;
    private String methodName;
    private String description;
    private LogLevel logLevel;
    private LogAction logAction;
    private Date dateTime;

    public EventLog(long id, String methodName, String description, LogLevel logLevel, LogAction logAction, Date dateTime) {
        this.id = id;
        this.methodName = methodName;
        this.description = description;
        this.logLevel = logLevel;
        this.logAction = logAction;
        this.dateTime = dateTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMethodName() {
        return methodName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LogLevel getLogLevel() {
        return logLevel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LogAction getLogAction() {
        return logAction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date getDateTime() {
        return dateTime;
    }

    /**
     * Compare event logs based on date
     * @param iEventLog
     * @return
     */
    @Override
    public int compareTo(IEventLog iEventLog) {
        return this.getDateTime().compareTo(iEventLog.getDateTime());
    }
}
