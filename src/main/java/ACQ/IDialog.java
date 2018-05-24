package ACQ;

import ACQ.IMeeting;
import ACQ.IUser;

import java.util.Collection;
import java.util.Set;

public interface IDialog {
    /**
     * Create a new meeting and add it to the collection of meetings
     * @return new IMeeting
     */
    IMeeting createMeeting(IUser currentUser, int meetingNumber);

    /**
     * Cancel a meeting, and remove it from the collection of meetings
     * @return true if the meeting was cancelled successfully
     */
    boolean cancelMeeting(IMeeting meetingToCancel, IUser currentUser);

    /**
     * Find meetings based on a participant's SSN
     * @return the meetings in which the participant is participating.
     */
    Collection<IMeeting> getMeetingBySSN(String ssn);

    /**
     * Get all the meetings inside the dialog.
     * @return all meetings
     */
    Set<IMeeting> getMeetings();
}
