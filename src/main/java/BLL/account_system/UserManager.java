package BLL.account_system;

import ACQ.IAccount;
import ACQ.ISigningService;
import ACQ.IUser;
import ACQ.IUserManager;

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
        return null;
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
