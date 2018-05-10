import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import BLL.ACQ.*;
import BLL.meeting.Dialog;
import BLL.meeting.IHttp;
import BLL.ACQ.IMeeting;
import DAL.http_request_utility.HttpRequestUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public class SummonCitizenToMeetingTest {
    private Dialog dialog;
    private IMeeting meeting;
    private IUser creatorOfMeeting = createUser("1111");
    private IUser participant = createUser("1234");

    private IHttp httpClient = new IHttp() {
        @Override
        public byte[] makeHttpRequest(String urlString, Map<String, Object> query, HttpMethod method, HttpAcceptType acceptType) throws IOException {
            return HttpRequestUtility.makeHttpRequest(urlString, query, method, acceptType);
        }
    };

    @BeforeEach
    public void initialize() {
        dialog = new Dialog(httpClient);
        meeting = dialog.createMeeting(creatorOfMeeting);
        meeting.addParticipant(participant, createUser("0987"));
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