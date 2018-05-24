import ACQ.IUser;
import BLL.getter.user_getter.GetUser;
import BLL.getter.user_getter.IGetUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetUserTest {

    @Test
    public void TestUserGetter() {
        IGetUser getUser = new GetUser(TestHelper.getHttpClient());

        IUser userResult = getUser.getUser("1104694124");

        assertEquals("Jonathan", userResult.getFirstName());
        assertEquals("Gregersen", userResult.getLastName());
        assertEquals("51364257", userResult.getPhoneNumber());
        assertEquals("JonathanSGregersen@teleworm.us", userResult.getEmail());

        assertEquals("Lærkevej", userResult.getAddress().getStreetName());
        assertEquals("127", userResult.getAddress().getHouseNumber());
        assertEquals("3120", userResult.getAddress().getZipCode());
        assertEquals("Glumsø", userResult.getAddress().getCity());
        assertEquals("Samsø", userResult.getAddress().getMunicipality());
        assertEquals("Denmark", userResult.getAddress().getCountry());
    }
}