package BLL.account_system;

import ACQ.*;
import BLL.security_system.SecuritySystem;

import java.util.Locale;

public final class UserManager implements IUserManager {
	private IDefaultService defaultService;
    private ISigningService service;
    private Account signedInAccount;
    private User signedInUser;

    public UserManager(IDefaultService defaultService, ISigningService service) {
    	this.defaultService = defaultService;
        this.service = service;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IAccount getSignedInAccount() {
        return signedInAccount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IUser getSignedInUser() {
        return signedInUser;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IProfile signIn(String username, String password) {
	    IProfile profile = null;

	    username = username.toLowerCase(Locale.ROOT);

	    if(defaultService.accountExists(username)) {
            profile = service.signIn(username, password);
            // hvis man allerede er logget ind, er profile null
            if (profile != null && profile.getUser() != null && profile.getAccount() != null) {
                SecuritySystem.getInstance().setAccount(profile.getAccount());
            } else {
                profile = null;
            }
        }

	    return profile;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean signOut(String accountName) {
        this.signedInAccount = null;
        this.signedInUser = null;

        return service.signOut(accountName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean signUpUser(String ssn) {
        return service.signUpUser(ssn);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean signUpUser(String ssn, String firstName, String lastName, String phoneNumber, String email) {
        return service.signUpUser(ssn, firstName, lastName, phoneNumber, email);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean signUpUser(IUser user) {
        return service.signUpUser(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean signUpAccount(String ssn, String username, String password, int securityLevel) {
        boolean signedUp = false;

    	if(defaultService.userExists(ssn)) signedUp = service.signUpAccount(ssn, username, password, securityLevel);

    	return signedUp;
    }
}
