import static org.junit.jupiter.api.Assertions.assertEquals;

import BLL.ACQ.AccessLevel;
import BLL.ACQ.IAddress;
import BLL.ACQ.IUser;
import BLL.summon_citizen_to_meeting.Dialog;
import BLL.summon_citizen_to_meeting.IMeeting;
import org.junit.jupiter.api.Test;

public class SummonCitizenToMeetingTest {

    @Test
    public void MeetingTest() {
        Dialog dialog = new Dialog();

        // assert statements
        IMeeting meeting = dialog.createMeeting();
        meeting.addParticipant(new IUser() {
            @Override
            public AccessLevel getAccessLevel() {
                return AccessLevel.USER;
            }

            @Override
            public void setSocialSecurityNumber(String socialSecurityNumber) {

            }

            @Override
            public void setName(String firstName, String lastName) {

            }

            @Override
            public void setAddress(IAddress address) {

            }

            @Override
            public void setPhoneNumber(String phoneNumber) {

            }
        });
        meeting.setMeetingDate(2018, 6, 25, 6, 4);
        meeting.setInformation("HEJ");

        if (meeting.sendMessage()) {
            System.out.println("Mødet blev sendt!");
            System.out.println(meeting);
        }

        assertEquals("HEJ", meeting.getInformation());
    }
}