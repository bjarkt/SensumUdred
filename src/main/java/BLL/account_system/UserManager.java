package BLL.account_system;

import ACQ.IAccount;
import ACQ.ISigningService;
import ACQ.IUser;
import ACQ.IUserManager;
import BLL.security_system.SecuritySystem;
import DAL.database.IDatabaseService;

import java.util.Set;

public final class UserManager implements IUserManager, ISigningService {
    private IDatabaseService dbService;
    private Account signedInAccount;
    private User signedInUser;

    public UserManager(IDatabaseService dbService) {
        this.dbService = dbService;
    }

    @Override
    public IAccount getSignedInAccount() {
        return signedInAccount;
    }

    @Override
    public IUser getSignedInUser() {
        return signedInUser;
    }

    @Override
    public IUser signIn(String username, String password) {
	    IUser user = null;

	    if(accountExists(username)) user = dbService.signIn(username, password);
	    SecuritySystem.getInstance().setUser(user);

	    return user;
    }

    @Override
    public boolean signOut(String accountName) {
        this.signedInAccount = null;
        this.signedInUser = null;

        return dbService.signOut(accountName);
    }

    @Override
    public boolean signUpUser(String ssn) {
        return dbService.signUpUser(ssn);
    }

    @Override
    public boolean signUpUser(String ssn, String firstName, String lastName, String phoneNumber, String email) {
        return dbService.signUpUser(ssn, firstName, lastName, phoneNumber, email);
    }

    @Override
    public boolean signUpUser(IUser user) {
        return dbService.signUpUser(user);
    }

    @Override
    public boolean signUpAccount(String ssn, String username, String password, int securityLevel) {
        boolean signedUp = false;

    	if(userExists(ssn)) signedUp = dbService.signUpAccount(ssn, username, password, securityLevel);

    	return signedUp;
    }

    @Override
    public boolean accountExists(String accountName) {
        return dbService.accountExists(accountName);
    }

    @Override
    public boolean userExists(String ssn) {
        return dbService.userExists(ssn);
    }

    @Override
    public boolean lockAccount(String accountName) {
        return dbService.lockAccount(accountName);
    }

    @Override
    public boolean unlockAccount(String accountName) {
        return dbService.unlockAccount(accountName);
    }

    @Override
    public boolean changeSecurityLevel(String accountName, int newSecurityLevel) {
        return dbService.changeSecurityLevel(accountName, newSecurityLevel);
    }

    @Override
    public boolean changePassword(String accountName, String newPassword) {
        return dbService.changePassword(accountName, newPassword);
    }

    @Override
    public Set<IUser> getAllUsers(int limit) {
        return dbService.getAllUsers(limit);
    }

    @Override
    public Set<IAccount> getAllAccounts(int limit) {
        return dbService.getAllAccounts(limit);
    }
}
