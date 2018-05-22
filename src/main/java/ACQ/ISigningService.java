package ACQ;

public interface ISigningService {
    /**
     * Signs a user into the user manager.
     * If a user already is signed in, it returns null.
     * @param username any username
     * @param password any password
     * @return user, if username and password match; otherwise null
     */
    IUser signIn(String username, String password);

    /**
     * Signs a user out of the system.
     * @param accountName any account name
     * @return true, if successful; otherwise false
     */
    boolean signOut(String accountName);

	/**
	 * Register a user based on their social security number.
	 * The system will automatically receive all the information.
	 * @param SSN any ssn
	 * @return true, if successful; false, if user already exists
	 */
	boolean signUpUser(String SSN);

	/**
	 * Register a user manually by inserting all of their information.
	 * @param SSN any ssn
	 * @param firstName any first name
	 * @param lastName any last name
	 * @param phoneNumber any phone number
	 * @param email any email
	 * @return true, if successful; false, if user or email already exists
	 */
	boolean signUpUser(String SSN, String firstName, String lastName, String phoneNumber, String email);

	/**
	 * Register a user by a
	 * @param user
	 * @return
	 */
	boolean signUpUser(IUser user);

    /**
     * Register an account.
     * @param username any username
     * @param password any password
     * @param securityLevel any security level
     * @return true, if successful; false, if username exists
     */
    boolean signUpAccount(String username, String password, int securityLevel);
}