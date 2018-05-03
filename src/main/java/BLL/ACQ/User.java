package BLL.ACQ;

public class User implements IUser{

    private AccessLevel accessLevel;
    private String firstName;
    private String lastName;
    private String ssn;
    private IAddress address;
    private String phoneNumber;

    public User(String firstName, String lastName, String ssn){
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.accessLevel = AccessLevel.USER;
    }

    @Override
    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    @Override
    public void setName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public void setSocialSecurityNumber(String ssn) {
        this.ssn = ssn;
    }

    @Override
    public void setAddress(IAddress address) {
        this.address = address;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return this.firstName + "\t" + this.lastName + "\t" + this.ssn;
    }
}
