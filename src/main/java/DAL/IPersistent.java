package DAL;

import BLL.ACQ.HttpAcceptType;
import BLL.ACQ.HttpMethod;

import java.io.IOException;
import java.util.Map;

public interface IPersistent {
    byte[] makeHttpRequest(String urlString, Map<String, Object> query, HttpMethod method, HttpAcceptType acceptType) throws IOException;
}