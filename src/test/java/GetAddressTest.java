import ACQ.HttpAcceptType;
import ACQ.HttpMethod;
import ACQ.IAddress;
import ACQ.IHttp;
import BLL.address_getter.GetAddress;
import BLL.address_getter.IGetAddress;
import DAL.http_request_utility.HttpRequestUtility;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

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
        assertEquals("Lærkevej", addressResult.getStreetName());
        assertEquals("127", addressResult.getHouseNumber());
        assertEquals("3120", addressResult.getZipCode());
        assertEquals("Glumsø", addressResult.getCity());
        assertEquals("Samsø", addressResult.getMunicipality());
        assertEquals("Denmark", addressResult.getCountry());
    }
}