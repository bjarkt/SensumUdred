package DAL;

import BLL.ACQ.HttpAcceptType;
import BLL.ACQ.HttpMethod;
import BLL.meeting.IMeeting;

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