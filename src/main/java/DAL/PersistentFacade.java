package DAL;

import BLL.ACQ.HttpAcceptType;
import BLL.ACQ.HttpMethod;
import BLL.meeting.IMeeting;
import DAL.database.IDatabase;
import DAL.database.PostgreSqlDatabase;
import DAL.http_request_utility.HttpRequestUtility;

import java.io.IOException;
import java.util.Map;

public class PersistentFacade implements IPersistent {
    private IDatabase database;

    public PersistentFacade() {
        this.database = new PostgreSqlDatabase();
    }

    @Override
    public byte[] makeHttpRequest(String urlString, Map<String, Object> query, HttpMethod method, HttpAcceptType acceptType) throws IOException {
        return HttpRequestUtility.makeHttpRequest(urlString, query, method, acceptType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean fileMeeting(IMeeting meeting) {
        return database.fileMeeting(meeting);
    }
}