package BLL.ACQ;

public interface IUser {

	/**
	 * Gets the user's entry level.
	 * @return	int	the user's entry level.
	 */
	int getAccessLevel();

	/**
	 * Get the user's full name
	 * @return	the user's full name.
	 */
	String getName();

	/**
	 * Get the user's first name.
	 * @return	user's first name.
	 */
	String getFirstName();

	/**
	 * Get the user's last name.
	 * @return	user's last name.
	 */
	String getLastName();


	/**
	 * Sets the user's name
	 * @param firstName	user's first name.
	 * @param lastName user's last name.
	 */
	void setName(String firstName, String lastName);

	/**
	 * Sets the user's social security number.
	 * @param ssn user's social security number.
	 */
	void setSocialSecurityNumber(String ssn);

	/**
	 * Sets the user's address.
	 * @param address	the address.
	 */
	void setAddress(IAddress address);

	/**
	 * Sets the user's phone number.
	 * @param phoneNumber	user's phone number.
	 */
	void setPhoneNumber(String phoneNumber);

	/**
	 * Get the user's social security number.
	 * @return user's social security number.
	 */
	String getSocialSecurityNumber();


}