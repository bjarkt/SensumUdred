package BLL.meeting;

import BLL.ACQ.IUser;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Dialog implements IDialog {
    private Set<IMeeting> meetings;

    public Dialog() {
        meetings = new HashSet<>();
    }

    /**
     * {@inheritDoc}
     */
    public IMeeting createMeeting() {
        IMeeting meeting = new Meeting();
        meetings.add(meeting);
        return meeting;
    }

    @Override
    public boolean cancelMeeting(IMeeting meetingToCancel) {
        meetingToCancel.cancelMeeting();
        meetings.remove(meetingToCancel);
        // todo: set status in db to 'cancelled'
        return true;
    }

    @Override
    public Collection<IMeeting> getMeetingBySSN(String ssn) {
        Set<IMeeting> meetingsToBeFound = new HashSet<>();

        for (IMeeting meeting : meetings) {
            for (IUser user : meeting.getParticipants()) {
                if (user.getSocialSecurityNumber().equals(ssn)) {
                    meetingsToBeFound.add(meeting);
                    break;
                }
            }
        }
        return meetingsToBeFound;
    }
}
