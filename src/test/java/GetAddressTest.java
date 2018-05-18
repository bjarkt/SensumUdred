import BLL.ACQ.HttpAcceptType;
import BLL.ACQ.HttpMethod;
import BLL.ACQ.IAddress;
import BLL.ACQ.IHttp;
import BLL.address_getter.GetAddress;
import BLL.address_getter.IGetAddress;
import DAL.ConfigManager;
import DAL.http_request_utility.HttpRequestUtility;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetAddressTest {

    @Test
    public void TestAddressGetter() {
        IGetAddress getAddress = new GetAddress(new IHttp() {
            @Override
            public byte[] makeHttpRequest(String urlString, Map<String, Object> query, HttpMethod method, HttpAcceptType acceptType) throws IOException {
                return HttpRequestUtility.makeHttpRequest(urlString, query, method, acceptType);
            }
        });

        IAddress addressResult = getAddress.getAddress("1104694124");
        assertEquals("Glumsø", addressResult.getCity());
        assertEquals("Samsø", addressResult.getMunicipality());
        assertEquals("Lærkevej", addressResult.getStreetName());
    }
}