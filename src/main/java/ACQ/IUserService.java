package ACQ;

public interface IUserService {
	/**
	 * Returns a true or false, depending on an account exists.
	 * @param account any account
	 * @return true, if accounts exists; otherwise false
	 */
	boolean accountExists(IAccount account);

	/**
	 * Returns a true or false, depending on an user exists.
	 * @param user any user
	 * @return true, if user exists; otherwise false
	 */
	boolean userExists(IUser user);

	/**
	 * Lock an account.
	 * If account is already locked, it returns false.
	 * @param account any account
	 * @return true, if locking was successful; otherwise false
	 */
	boolean lockAccount(IAccount account);

	/**
	 * Unlock an account.
	 * If account is already unlocked, it returns false.
	 * @param account any account
	 * @return true, if unlocking was successful; otherwise false
	 */
	boolean unlockAccount(IAccount account);

	/**
	 * Change the security level of an account.
	 * @param account any account
	 * @param newSecurityLevel any security level
	 * @return true, if change was successful; otherwise false
	 */
	boolean changeSecurityLevel(IAccount account, int newSecurityLevel);

	/**
	 * Change the password of an account.
	 * @return
	 */
	boolean changePassword(IAccount account, String newPassword);
}
