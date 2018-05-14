package BLL.meeting;

import BLL.ACQ.IMeeting;
import BLL.ACQ.IUser;

import java.util.Collection;

public interface IDialog {
    /**
     * Create a new meeting and add it to the collection of meetings
     * @return new IMeeting
     */
    public IMeeting createMeeting(IUser currentUser);

    /**
     * Cancel a meeting, and remove it from the collection of meetings
     * @return true if the meeting was cancelled successfully
     */
    public boolean cancelMeeting(IMeeting meetingToCancel, IUser currentUser);

    /**
     * Find meetings based on a participant's SSN
     * @return the meetings in which the participant is participating.
     */
    Collection<IMeeting> getMeetingBySSN(String ssn);
}
