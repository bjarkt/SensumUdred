package DAL;

import ACQ.HttpAcceptType;
import ACQ.HttpMethod;
import ACQ.IMeeting;

import java.io.IOException;
import java.util.Map;

public interface IPersistent {
    byte[] makeHttpRequest(String urlString, Map<String, Object> query, HttpMethod method, HttpAcceptType acceptType) throws IOException;

    /**
     * Files a meeting into the database.
     * @param meeting any meeting
     * @return successful or not
     */
    boolean fileMeeting(IMeeting meeting);
}