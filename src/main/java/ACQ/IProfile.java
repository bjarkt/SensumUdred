package ACQ;

public interface IProfile {
	/**
	 * Get the user.
	 * @return a user
	 */
	IUser getUser();

	/**
	 * Get the account.
	 * @return an account
	 */
	IAccount getAccount();
}
