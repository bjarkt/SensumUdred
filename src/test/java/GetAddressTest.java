import ACQ.IAddress;
import BLL.account_system.Address;
import BLL.getter.address_getter.GetAddress;
import BLL.getter.address_getter.IGetAddress;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetAddressTest {

    @Test
    public void TestAddressGetter() {
        IGetAddress getAddress = new GetAddress(TestHelper.getHttpClient(), Address.class);

        IAddress addressResult = getAddress.getAddress("1104694124");
        assertEquals("Lærkevej", addressResult.getStreetName());
        assertEquals("127", addressResult.getHouseNumber());
        assertEquals("3120", addressResult.getZipCode());
        assertEquals("Glumsø", addressResult.getCity());
        assertEquals("Samsø", addressResult.getMunicipality());
        assertEquals("Denmark", addressResult.getCountry());
    }
}