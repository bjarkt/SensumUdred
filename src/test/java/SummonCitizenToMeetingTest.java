import ACQ.*;
import BLL.eboks.EBoks;
import BLL.meeting.Dialog;
import DAL.http_request_utility.HttpRequestUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SummonCitizenToMeetingTest {
    private Dialog dialog;
    private IMeeting meeting;
    private IUser creatorOfMeeting = TestHelper.createUser("1111");
    private IUser participant = TestHelper.createUser("1234");

    private IHttp httpClient = TestHelper.getHttpClient();

    @BeforeEach
    public void initialize() {
        dialog = new Dialog(httpClient, new EBoks(httpClient));
        meeting = dialog.createMeeting(creatorOfMeeting);
        meeting.addParticipant(participant, TestHelper.createUser("0987"));
        meeting.setMeetingDate(2018, 6, 25, 6, 4);
        meeting.setInformation("HEJ");
    }

    @Test
    public void MeetingTest() {
        boolean meetingMessageisSent = meeting.sendMeetingMessage();

        assertTrue(meetingMessageisSent);
        assertEquals("HEJ", meeting.getInformation());
    }

    @Test
    public void cancelMeetingTest() {
        Collection<IMeeting> meetings = dialog.getMeetingBySSN("1234");
        Optional<IMeeting> meetingToCancel = meetings.stream().findFirst();
        boolean meetingIsCancelled = false;

        assert meetingToCancel.isPresent();
        if (dialog.cancelMeeting(meetingToCancel.get(), creatorOfMeeting)) {
            meetingIsCancelled = true;
        }

        assertTrue(meetingToCancel.get().getInformation().startsWith("VIGTIGT: Dette møde er blevet aflyst!"));
        assertTrue(meetingIsCancelled);
    }
}