package BLL.account_system;

import ACQ.*;

public class UserManager implements IUserManager, ISigningService {
    private static UserManager INSTANCE;
    private User user;

    private UserManager(){

    }

    public static UserManager getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new UserManager();
        }

        return INSTANCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IUser signIn(String username, String password) {
        return new IUser() {
            @Override
            public String getSocialSecurityNumber() {
                return "1234567890";
            }

            @Override
            public String getFirstName() {
                return "Lasse";
            }

            @Override
            public String getLastName() {
                return "Traberg";
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
                return new IAccount() {
                    @Override
                    public String getUsername() {
                        return "lassetraberg";
                    }

                    @Override
                    public boolean isLocked() {
                        return false;
                    }

                    @Override
                    public int getSecurityLevel() {
                        return 1000;
                    }
                };
            }

            @Override
            public String getName() {
                return "Lasse Traberg";
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean signOut() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean signUpAccount(String username, String password, int securityLevel) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean signUpUser(String SSN) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean signUpUser(String SSN, String firstName, String lastName, String phoneNumber, String email) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean signUpUser(IUser user) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getSignedInUser() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean accountExists(IAccount account) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean userExists(IUser user) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean changeSecurityLevel(IAccount account, int newSecurityLevel) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean changePassword(IAccount account, String newPassword) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean lockAccount(IAccount account) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean unlockAccount(IAccount account) {
        return false;
    }
}
