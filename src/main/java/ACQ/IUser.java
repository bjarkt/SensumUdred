package ACQ;

public interface IUser {

	/**
	 * Returns the account the user signed in with.
	 * @return the account
	 */
	IAccount getAccount();

	/**
	 * Get the user's full name
	 * @return the user's full name.
	 */
	String getName();

	/**
	 * Get the user's first name.
	 * @return user's first name.
	 */
	String getFirstName();

	/**
	 * Get the user's last name.
	 * @return user's last name.
	 */
	String getLastName();

	/**
	 * Get the user's social security number (SSN).
	 * @return user's social security number.
	 */
	String getSocialSecurityNumber();


}