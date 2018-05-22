package BLL.account_system;

import ACQ.IAddress;
import ACQ.IUser;

public class User implements IUser {
    private String ssn;
    private String firstName;
    private String lastName;
    private Address address;
    private String phoneNumber;
    private String email;

    public User(String ssn, String firstName, String lastName){
        this.ssn = ssn;
        this.firstName = firstName;
        this.lastName = lastName;
    }

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
        return this.firstName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLastName() {
        return this.lastName;
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
        return this.firstName + " " + this.lastName;
    }

    /**
     * Set the first name and the last name of the user.
     * @param firstName any first name
     * @param lastName any last name
     */
    public void setName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Set the address of the user.
     * @param address any address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Set the phone number of the user.
     * @param phoneNumber any phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Set the email of the user.
     * @param email any email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.ssn + "\t" + this.firstName + "\t" + this.lastName;
    }
}
