package DAL;

import ACQ.*;
import DAL.database.IDatabaseService;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public interface IPersistent {
    /**
     * Get an http client
     * @return IHttp http client
     */
    IHttp getHttp();

    IDatabaseService getDatabaseService();
}