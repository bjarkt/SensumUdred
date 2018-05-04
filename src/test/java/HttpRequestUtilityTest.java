import BLL.ACQ.HttpAcceptType;
import BLL.ACQ.HttpMethod;
import DAL.http_request_utility.HttpRequestUtility;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class HttpRequestUtilityTest {

    @Test
    public void makeHttpRequestTest() throws IOException {
        Map<String, Object> query = new HashMap<>();

        query.put("cpr", 1234567890);
        query.put("service", -1);
        query.put("department", 0);

        String textResponse = new String(HttpRequestUtility.makeHttpRequest("https://sensumudred-api.herokuapp.com/api/case-request",
                query, HttpMethod.POST, HttpAcceptType.TEXT));
        byte[] pdfResponse = (HttpRequestUtility.makeHttpRequest("https://sensumudred-api.herokuapp.com/files/hospital-doctor-1234567890.pdf",
                null, HttpMethod.GET, HttpAcceptType.PDF));

        /* Uncomment this to test if the pdf works */
        //Path path = Paths.get("./file.pdf");
        //Files.write(path, pdfResponse);

        assert pdfResponse.length > 0;
        assert textResponse.length() > 0;
    }
}