import static org.junit.jupiter.api.Assertions.assertEquals;

import DAL.ConfigManager;
import DAL.http_request_utility.HttpAcceptType;
import DAL.http_request_utility.HttpMethod;
import DAL.http_request_utility.HttpRequestUtility;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class HttpRequestUtilityTest {

    @Test
    public void makeHttpRequestTest() throws IOException {
        Map<String, String> query = new HashMap<>();

        query.put("cpr", "1234567890");
        query.put("service", "-1");
        query.put("department", "0");

        String textResponse = new String(HttpRequestUtility.makeHttpRequest("https://sensumudred-api.herokuapp.com/api/case-request",
                query, HttpMethod.POST, HttpAcceptType.TEXT));
        byte[] pdfResponse = (HttpRequestUtility.makeHttpRequest("https://sensumudred-api.herokuapp.com/files/hospital-doctor-1234567890.pdf",
                query, HttpMethod.GET, HttpAcceptType.PDF));
        System.out.println(textResponse);
    }
}