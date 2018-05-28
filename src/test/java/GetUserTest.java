import ACQ.IUser;
import BLL.account_system.Address;
import BLL.account_system.User;
import BLL.getter.user_getter.GetUser;
import BLL.getter.user_getter.IGetUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GetUserTest {

    @Test
    public void TestUserGetter() {
        IGetUser getUser = new GetUser(TestHelper.getHttpClient(), User.class);

        IUser userResult = getUser.getUser("1104694124");

        assertEquals("Jeppe", userResult.getFirstName());
        assertEquals("Damgaard", userResult.getLastName());
        assertEquals("25208168", userResult.getPhoneNumber());
        assertEquals("JeppeSDamgaard@teleworm.us", userResult.getEmail());

        assertEquals("Skovvej", userResult.getAddress().getStreetName());
        assertEquals("61", userResult.getAddress().getHouseNumber());
        assertEquals("7550", userResult.getAddress().getZipCode());
        assertEquals("Trustrup", userResult.getAddress().getCity());
        assertEquals("Rebild", userResult.getAddress().getMunicipality());
        assertEquals("Denmark", userResult.getAddress().getCountry());
    }

    @Test
    public void TestBadUserGetter() {
        IGetUser getUser = new GetUser(TestHelper.getHttpClient(), User.class);

        IUser userResult = getUser.getUser("bad");

        assertNull(userResult);
    }
}