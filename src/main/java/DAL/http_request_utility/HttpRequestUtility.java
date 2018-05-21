package DAL.http_request_utility;

import ACQ.HttpAcceptType;
import ACQ.HttpMethod;
import DAL.ConfigManager;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpRequestUtility {
    /**
     * Make an HTTP request to the URL
     * @param urlString Which url to request
     * @param query Request queries, as a map
     * @param method Which method to use (POST/GET/etc..)
     * @param acceptType What do you except the server will output? text/pdf/etc...
     * @return byte array of output from server
     * @throws IOException
     */
    public static byte[] makeHttpRequest(String urlString, Map<String, Object> query, HttpMethod method, HttpAcceptType acceptType) throws IOException {
        if (acceptType == HttpAcceptType.PDF && query != null) {
            throw new IllegalArgumentException("query must be null when using pdf HttpAcceptType");
        }
        URL url = new URL(urlString);
        byte[] data = null;

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod(method.getName());
        conn.setUseCaches(false);

        conn.addRequestProperty("Accept", "application/" + acceptType.getName());
        conn.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.addRequestProperty("api-key", ConfigManager.getInstance().getProperties().getProperty(("api-key")));

        conn.setDoInput(true);

        if (query != null) {
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            String queryString = queryMapToString(query);
            os.write(queryString.getBytes());
            os.flush();
        }

        if (conn.getResponseCode() != 200) {
            throw new IOException("Error: HTTP Response Code " + conn.getResponseCode());
        } else {
            switch (acceptType) {
                case PDF:
                    data = readBinary(conn.getInputStream(), conn.getContentLength());
                    break;
                case TEXT:
                case JSON:
                    data = readText(conn.getInputStream());
                    break;
                default:
                    throw new IllegalArgumentException("HttpAcceptType not supported");
            }

        }

        conn.disconnect();


        return data;
    }

    /**
     * Read binary data from the inputstream
     * @param inputStream inputstream from the {@link HttpURLConnection}
     * @param contentLength how many bytes is the content?
     * @return byte array of read data
     * @throws IOException
     */
    private static byte[] readBinary(InputStream inputStream, int contentLength) throws IOException {
        byte[] buffer = new byte[1024];
        ByteArrayOutputStream data = new ByteArrayOutputStream(contentLength);

        int length;
        while((length = inputStream.read(buffer)) > -1) {
            data.write(buffer, 0, length);
        }

        return data.toByteArray();
    }

    /**
     * Read text data from an input stream
     * @param inputStream inputstream from the {@link HttpURLConnection}
     * @return string as a byte array
     * @throws IOException
     */
    private static byte[] readText(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            sb.append(line + System.lineSeparator());
        }

        return sb.toString().getBytes();
    }

    /**
     * Transform a query map into a query string
     * @param query map of query key value pairs
     * @return string of query key value pairs
     */
    private static String queryMapToString(Map<String, Object> query) {
        StringBuilder sb = new StringBuilder();

        boolean firstPair = true;

        for (Map.Entry<String, Object> entry : query.entrySet()) {
            if (!firstPair) {
                sb.append("&");
            }
            sb.append(entry.getKey() + "=" + entry.getValue().toString());
            firstPair = false;
        }

        return sb.toString();
    }
}
