package ACQ;

import java.util.Set;

public interface IAdminService {
	/**
	 * Lock an account.
	 * If account is already locked, it returns false.
	 * @param accountName any account name
	 * @return true, if locking was successful; otherwise false
	 */
	boolean lockAccount(String accountName);

	/**
	 * Unlock an account.
	 * If account is already unlocked, it returns false.
	 * @param accountName any account name
	 * @return true, if unlocking was successful; otherwise false
	 */
	boolean unlockAccount(String accountName);

	/**
	 * Change the security level of an account.
	 * @param accountName any account name
	 * @param newSecurityLevel any security level
	 * @return true, if change was successful; otherwise false
	 */
	boolean changeSecurityLevel(String accountName, int newSecurityLevel);

	/**
	 * Change the password of an account.
	 * @return true, if change was successful; otherwise false
	 */
	boolean changePassword(String accountName, String newPassword);
}
