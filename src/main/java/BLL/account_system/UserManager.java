package BLL.account_system;

import ACQ.*;
import DAL.database.IDatabaseService;

import java.util.Set;

public class UserManager implements IUserManager, ISigningService {
    private IDatabaseService dbService;
    private User signedUser;
    private Account signedAccount;

    public UserManager(IDatabaseService dbService) {
        this.dbService = dbService;
    }

    @Override
    public IUser signIn(String username, String password) {
        return null;
    }

    @Override
    public boolean signOut(String accountName) {
        return false;
    }

    @Override
    public boolean signUpUser(String SSN) {
        return false;
    }

    @Override
    public boolean signUpUser(String SSN, String firstName, String lastName, String phoneNumber, String email) {
        return false;
    }

    @Override
    public boolean signUpUser(IUser user) {
        return false;
    }

    @Override
    public boolean signUpAccount(String username, String password, int securityLevel) {
        return false;
    }

    @Override
    public boolean accountExists(String accountName) {
        return false;
    }

    @Override
    public boolean userExists(String ssn) {
        return false;
    }

    @Override
    public boolean lockAccount(String accountName) {
        return false;
    }

    @Override
    public boolean unlockAccount(String accountName) {
        return false;
    }

    @Override
    public boolean changeSecurityLevel(String accountName, int newSecurityLevel) {
        return false;
    }

    @Override
    public boolean changePassword(String accountName, String newPassword) {
        return false;
    }

    @Override
    public Set<IUser> getAllUsers() {
        return null;
    }

    @Override
    public Set<IAccount> getAllAccounts() {
        return null;
    }

    @Override
    public boolean getSignedInUser() {
        return false;
    }
}
