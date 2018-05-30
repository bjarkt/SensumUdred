package ACQ;

import java.io.IOException;
import java.util.Map;

public interface IHttp {
    /**
     * Make an http request to the url, with the data in the query, with method as the request method, expecting a result of type acceptType.
     * IMPORTANT: Because of how Java's HttpUrlConnection works, query must be null, when using GET method.
     * @param urlString Which url to make the request to
     * @param query map of data to send to the url
     * @param method what method to use
     * @param acceptType what data type is expected to return
     * @return byte array of results
     * @throws IOException When the response code isn't 200 (OK).
     */
    byte[] makeHttpRequest(String urlString, Map<String, Object> query, HttpMethod method, HttpAcceptType acceptType) throws IOException;
}
