import static org.junit.jupiter.api.Assertions.assertEquals;

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
            public int getEntryLevel() {
                return 0;
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