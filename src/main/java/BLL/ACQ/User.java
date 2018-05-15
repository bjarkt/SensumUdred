package BLL.ACQ;

public class User implements IUser{

    private int accessLevel;
    private String firstName;
    private String lastName;
    private String ssn;
    private IAddress address;
    private String phoneNumber;

    public User(String firstName, String lastName, String ssn){
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.accessLevel = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getAccessLevel() {
        return accessLevel;
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

    @Override
    public void setName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSocialSecurityNumber(String ssn) {
        this.ssn = ssn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAddress(IAddress address) {
        this.address = address;
    }

    /**
     * {@inheritDoc}
     */
    @Override
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

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.firstName + "\t" + this.lastName + "\t" + this.ssn;
    }
}
