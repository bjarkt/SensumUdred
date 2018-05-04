package BLL.meeting;

import java.util.Collection;

public interface IDialog {
    /**
     * Create a new meeting and add it to the collection of meetings
     * @return new IMeeting
     */
    IMeeting createMeeting();

    /**
     * Cancel a meeting, and remove it from the collection of meetings
     * @return true if the meeting was cancelled successfully
     */
    boolean cancelMeeting(IMeeting meetingToCancel);

    /**
     * Find meetings based on a participant's SSN
     * @return the meetings in which the participant is participating.
     */
    Collection<IMeeting> getMeetingBySSN(String ssn);
}
