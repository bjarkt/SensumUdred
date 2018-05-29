import ACQ.IAddress;
import ACQ.IDatabaseService;
import ACQ.IHttp;
import ACQ.IUser;
import DAL.PersistentFacade;
import DAL.database.DatabaseService;

public class TestHelper {
    private static IDatabaseService service;

    static {
        service = new DatabaseService();
    }

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
            public String getName() {
                return null;
            }
        };
    }

    public static IHttp getHttpClient() {
        return new PersistentFacade().getHttp();
    }

    public static IDatabaseService getDatabaseService() {
        return service;
    }
}
