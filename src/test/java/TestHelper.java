import ACQ.*;
import DAL.PersistentFacade;
import DAL.http_request_utility.HttpRequestUtility;

import java.io.IOException;
import java.util.Map;

public class TestHelper {
    public static IUser createUser(String ssn) {
        return new IUser() {
            @Override
            public String getSocialSecurityNumber() {
                return ssn;
            }

            @Override
            public String getFirstName() {
                return null;
            }

            @Override
            public String getLastName() {
                return null;
            }

            @Override
            public IAddress getAddress() {
                return null;
            }

            @Override
            public String getPhoneNumber() {
                return null;
            }

            @Override
            public String getEmail() {
                return null;
            }

            @Override
            public IAccount getAccount() {
                return null;
            }

            @Override
            public String getName() {
                return null;
            }
        };
    }

    public static IHttp getHttpClient() {
        return new PersistentFacade().getHttp();
    }
}
