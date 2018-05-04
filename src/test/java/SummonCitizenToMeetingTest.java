import static org.junit.jupiter.api.Assertions.assertEquals;

import BLL.ACQ.AccessLevel;
import BLL.ACQ.IAddress;
import BLL.ACQ.IUser;
import BLL.meeting.Dialog;
import BLL.meeting.IMeeting;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Optional;

public class SummonCitizenToMeetingTest {
    private Dialog dialog;
    private IMeeting meeting;

    @BeforeEach
    public void initialize() {
        dialog = new Dialog();
        meeting = dialog.createMeeting();
        meeting.addParticipant(createUser("1234"), createUser("0987"));
        meeting.setMeetingDate(2018, 6, 25, 6, 4);
        meeting.setInformation("HEJ");
    }

    @Test
    public void MeetingTest() {
        if (meeting.sendMeetingMessage()) {
            System.out.println("Mødet blev sendt!");
            System.out.println(meeting);
        }

        assertEquals("HEJ", meeting.getInformation());
    }

    @Test
    public void cancelMeetingTest() {
        Collection<IMeeting> meetings = dialog.getMeetingBySSN("1234");
        Optional<IMeeting> meetingToCancel = meetings.stream().findFirst();
        boolean meetingIsCancelled = false;

        assert meetingToCancel.isPresent();
        if (dialog.cancelMeeting(meetingToCancel.get())) {
            meetingIsCancelled = true;
        }

        assert meetingToCancel.get().getInformation().startsWith("VIGTIGT: Dette møde er blevet aflyst!");
        assert meetingIsCancelled;
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