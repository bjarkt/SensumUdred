package BLL.case_opening;

import BLL.ACQ.HttpAcceptType;
import BLL.ACQ.HttpMethod;

import java.io.IOException;
import java.util.Map;

public interface IHttp {

    /**
     * Set the required interface
     * @param required the interface that this component needs
     */
    byte[] makeHttpRequest(String urlString, Map<String, Object> query, HttpMethod method, HttpAcceptType acceptType) throws IOException;

}
