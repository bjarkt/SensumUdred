package DAL;

import ACQ.HttpAcceptType;
import ACQ.HttpMethod;
import ACQ.IMeeting;
import ACQ.IUser;
import DAL.database.DatabaseService;
import DAL.database.IDatabaseService;
import DAL.database.PostgreSqlDatabase;
import DAL.http_request_utility.HttpRequestUtility;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class PersistentFacade implements IPersistent {
    private IDatabaseService database;

    public PersistentFacade() {
        this.database = new DatabaseService();
    }

    @Override
    public byte[] makeHttpRequest(String urlString, Map<String, Object> query, HttpMethod method, HttpAcceptType acceptType) throws IOException {
        return HttpRequestUtility.makeHttpRequest(urlString, query, method, acceptType);
    }

	@Override
	public IDatabaseService getDatabaseService() {
		return database;
	}
}