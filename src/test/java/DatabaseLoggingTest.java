import ACQ.IEventLog;
import ACQ.ILoggingService;
import BLL.BusinessFacade;
import BLL.IBusiness;
import DAL.ConfigManager;
import DAL.IPersistent;
import DAL.PersistentFacade;
import DAL.database.providers.DatabaseLoggingProvider;
import org.junit.jupiter.api.Test;

import java.util.Properties;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatabaseLoggingTest {

    @Test
    public void GetLogsTest() {
        IBusiness business = new BusinessFacade();
        IPersistent persistent = new PersistentFacade();
        business.injectPersistent(persistent);
        ILoggingService loggingService = business.getLoggingService();
        Set<IEventLog> logs = loggingService.getEventLogs();
        System.out.println(logs);
    }
}