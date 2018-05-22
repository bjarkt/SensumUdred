package DAL;

import ACQ.IHttp;
import DAL.database.DatabaseService;
import DAL.database.IDatabaseService;
import DAL.http_request_utility.HttpRequestUtility;

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