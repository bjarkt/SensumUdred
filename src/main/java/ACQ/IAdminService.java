package ACQ;

public interface IAdminService {
	/**
	 * Change the ssn of a user.
	 * @param oldSSN the old ssn of the user
	 * @param newSSN the new ssn of the user
	 * @return true, if change was successful; otherwise false
	 */
	boolean changeSSN(String oldSSN, String newSSN);

	/**
	 * Change the first name of a user.
	 * @param ssn the ssn of the user
	 * @param newFirstName the new first name of the user
	 * @return
	 */
	boolean changeFirstName(String ssn, String newFirstName);

	/**
	 * Change the last name of a user.
	 * @param ssn the ssn of the user
	 * @param newLastName the new last name of the user
	 * @return
	 */
	boolean changeLastName(String ssn, String newLastName);

	/**
	 * Change the phone number of a user
	 * @param ssn the ssn of the user
	 * @param newPhoneNumber the new phone number of the user
	 * @return
	 */
	boolean changePhoneNumber(String ssn, String newPhoneNumber);

	/**
	 * Change the email of a user.
	 * @param ssn the ssn of the user
	 * @param newEmail the new email of the user
	 * @return
	 */
	boolean changeEmail(String ssn, String newEmail);

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
