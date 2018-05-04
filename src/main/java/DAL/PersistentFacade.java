package DAL;

import BLL.ACQ.HttpAcceptType;
import BLL.ACQ.HttpMethod;
import DAL.http_request_utility.HttpRequestUtility;

import java.io.IOException;
import java.util.Map;

public class PersistentFacade implements IPersistent {

    @Override
    public byte[] makeHttpRequest(String urlString, Map<String, Object> query, HttpMethod method, HttpAcceptType acceptType) throws IOException {
        return HttpRequestUtility.makeHttpRequest(urlString, query, method, acceptType);
    }
}