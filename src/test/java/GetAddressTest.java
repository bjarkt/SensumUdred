import ACQ.IAddress;
import BLL.account_system.Address;
import BLL.getter.address_getter.GetAddress;
import BLL.getter.address_getter.IGetAddress;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GetAddressTest {

    @Test
    public void TestAddressGetter() {
        IGetAddress getAddress = new GetAddress(TestHelper.getHttpClient(), Address.class);

        IAddress addressResult = getAddress.getAddress("1104694124");
        assertEquals("Skovvej", addressResult.getStreetName());
        assertEquals("61", addressResult.getHouseNumber());
        assertEquals("7550", addressResult.getZipCode());
        assertEquals("Trustrup", addressResult.getCity());
        assertEquals("Rebild", addressResult.getMunicipality());
        assertEquals("Denmark", addressResult.getCountry());
    }

    @Test
    public void TestBadAddressGetter() {
        IGetAddress getAddress = new GetAddress(TestHelper.getHttpClient(), Address.class);

        IAddress addressResult = getAddress.getAddress("bad");

        assertNull(addressResult);
    }
}