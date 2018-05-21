package ACQ;

public interface IAccount {

	/**
	 * Get the username of the account.
	 * @return the username
	 */
	String getUsername();

	/**
	 * Return whether is account is locked or not.
	 * @return true, if account is locked; otherwise false
	 */
	boolean isLocked();

	/**
	 * Get the user's security level.
	 * @return	int	the user's security level.
	 */
	int getSecurityLevel();
}
