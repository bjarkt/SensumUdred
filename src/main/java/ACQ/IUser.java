package ACQ;

public interface IUser {
	/**
	 * Get the user's social security number (SSN).
	 * @return user's social security number
	 */
	String getSocialSecurityNumber();

	/**
	 * Get the user's first name.
	 * @return user's first name
	 */
	String getFirstName();

	/**
	 * Get the user's last name.
	 * @return user's last name
	 */
	String getLastName();

	/**
	 * Get the user's address.
	 * @return user's address
	 */
	IAddress getAddress();

	/**
	 * Get the user's phone number.
	 * @return user's phone number
	 */
	String getPhoneNumber();

	/**
	 * Get the user's email.
	 * @return user's email
	 */
	String getEmail();

	/**
	 * Get the user's account, he/she signed in with.
	 * @return the account
	 */
	IAccount getAccount();

	/**
	 * Get the user's full name
	 * @return the user's full name.
	 */
	String getName();


}