package DAL.dataobject;

import ACQ.IAccount;
import ACQ.IAddress;
import ACQ.IUser;

public class UserData implements IUser {
	private String ssn;
	private String firstName;
	private String lastName;
	private IAddress address;
	private String phoneNumber;
	private String email;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getSocialSecurityNumber() {
		return ssn;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getFirstName() {
		return firstName;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getLastName() {
		return lastName;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IAddress getAddress() {
		return address;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getEmail() {
		return email;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return firstName + " " + lastName;
	}

	/**
	 * Set the ssn.
	 * @param ssn any ssn
	 */
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	/**
	 * Set the first name.
	 * @param firstName any first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Set the last name.
	 * @param lastName any last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Set the address.
	 * @param address any address
	 */
	public void setAddress(IAddress address) {
		this.address = address;
	}

	/**
	 * Set the phone number.
	 * @param phoneNumber any phone number
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Set the email.
	 * @param email any email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
}
