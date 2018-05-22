package DAL;

import ACQ.*;
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

    /**
     * {@inheritDoc}
     */
    @Override
    public IHttp getHttp() {
        return HttpRequestUtility::makeHttpRequest;
    }

	@Override
	public IDatabaseService getDatabaseService() {
		return database;
	}
}