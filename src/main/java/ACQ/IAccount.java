package ACQ;

public interface IAccount {

	/**
	 * Returns the username of the account.
	 * @return the username
	 */
	String getUsername();

	/**
	 * Gets the user's security level.
	 * @return	int	the user's security level.
	 */
	int getSecurityLevel();
}
