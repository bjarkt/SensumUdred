package BLL.ACQ;

import BLL.ACQ.HttpAcceptType;
import BLL.ACQ.HttpMethod;

import java.io.IOException;
import java.util.Map;

/**
 * Required interface for this component
 */
public interface IHttp {
    byte[] makeHttpRequest(String urlString, Map<String, Object> query, HttpMethod method, HttpAcceptType acceptType) throws IOException;
}
