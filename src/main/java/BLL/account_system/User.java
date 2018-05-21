package BLL.account_system;

import ACQ.IAccount;
import ACQ.IAddress;
import ACQ.IUser;

public class User implements IUser {
    private String firstName;
    private String lastName;
    private String ssn;
    private IAddress address;
    private String phoneNumber;
    private Account account;

    public User(Account account, String firstName, String lastName, String ssn){
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Sets the first name and the last name of the user.
     * @param firstName any first name
     * @param lastName any last name
     */
    public void setName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Sets the address of the user.
     * @param address any address
     */
    public void setAddress(IAddress address) {
        this.address = address;
    }

    /**
     * Sets the phone number of the user.
     * @param phoneNumber any phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSocialSecurityNumber() {
        return ssn;
    }

    @Override
    public IAccount getAccount() {
        return account;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.firstName + "\t" + this.lastName + "\t" + this.ssn;
    }
}
