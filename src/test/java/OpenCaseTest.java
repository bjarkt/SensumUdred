import BLL.ACQ.*;
import BLL.meeting.Dialog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OpenCaseTest {

    private IElucidation elucidation;
    private IUser caseworker = createUser("1111");
    private IUser citizen = createUser("1234");

    @BeforeEach
    public void initialize() {

    }

    @Test
    public void openCaseTest() {

    }







    private IUser createUser(String ssn) {
        return new IUser() {
            @Override
            public AccessLevel getAccessLevel() {
                return null;
            }

            @Override
            public void setName(String firstName, String lastName) {

            }

            @Override
            public void setSocialSecurityNumber(String ssn) {

            }

            @Override
            public void setAddress(IAddress address) {

            }

            @Override
            public void setPhoneNumber(String phoneNumber) {

            }

            @Override
            public String getSocialSecurityNumber() {
                return ssn;
            }
        };
    }


}
