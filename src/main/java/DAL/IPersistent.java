package DAL;

import ACQ.*;
import DAL.database.IDatabaseService;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public interface IPersistent {
    byte[] makeHttpRequest(String urlString, Map<String, Object> query, HttpMethod method, HttpAcceptType acceptType) throws IOException;

    IDatabaseService getDatabaseService();
}