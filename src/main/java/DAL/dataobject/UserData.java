package DAL.dataobject;

import ACQ.IAccount;
import ACQ.IAddress;
import ACQ.IUser;
import BLL.account_system.Account;
import BLL.account_system.Address;

public class UserData implements IUser {
	private String ssn;
	private String firstName;
	private String lastName;
	private Address address;
	private String phoneNumber;
	private String email;
	private Account account;

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
	public IAccount getAccount() {
		return account;
	}

	/**
	 * Returns null.
	 * @return null
	 */
	@Override
	public String getName() {
		return null;
	}

	/**
	 * Set the ssn for sending.
	 * @param ssn any ssn
	 */
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	/**
	 * Set the first name for sending.
	 * @param firstName any first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Set the last name for sending.
	 * @param lastName any last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Set the address for sending.
	 * @param address any address
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * Set the phone number for sending.
	 * @param phoneNumber any phone number
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Set the email for sending.
	 * @param email any email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Set the account for sending.
	 * @param account any account
	 */
	public void setAccount(Account account) {
		this.account = account;
	}

	public void clear() {

	}
}
