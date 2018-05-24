package BLL.meeting;

import ACQ.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Dialog implements IDialog {
    private Set<IMeeting> meetings;
    private IEBoks eBoks;

    public Dialog(IHttp http, IEBoks eboks) {
        meetings = new HashSet<>();
        this.eBoks = eboks;
        this.eBoks.setHttp(http);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IMeeting createMeeting(IUser currentUser, int meetingNumber) {
        IMeeting meeting = new Meeting(currentUser, eBoks, meetingNumber);
        meetings.add(meeting);
        return meeting;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean cancelMeeting(IMeeting meetingToCancel, IUser currentUser) {
        boolean meetingCancelledSuccessfully = meetingToCancel.cancelMeeting(currentUser);
        meetings.remove(meetingToCancel);
        // todo: set status in db to 'cancelled'
        return meetingCancelledSuccessfully;
    }

    /**
     * {@inheritDoc}
     */
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

    @Override
    public Set<IMeeting> getMeetings() {
        return meetings;
    }
}
