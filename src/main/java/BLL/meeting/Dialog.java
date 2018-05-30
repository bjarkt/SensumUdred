package BLL.meeting;

import ACQ.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Dialog implements IDialog {
    private Set<IMeeting> meetings;
    private IEBoks eBoks;

    public Dialog(IHttp http, IEBoks eboks, IDialog dialog) {
        this(http, eboks);

        if(dialog.getMeetings() != null) {
            for(IMeeting meeting : dialog.getMeetings()) {
                meetings.add(new Meeting(eboks, meeting));
            }
        }
    }

    public Dialog(IHttp http, IEBoks eboks) {
        meetings = new HashSet<>();
        this.eBoks = eboks;
        this.eBoks.setHttp(http);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IMeeting createMeeting(IUser currentUser) {
        int largestNum = 0;
        for (IMeeting meeting : meetings) {
            largestNum = Math.max(largestNum, meeting.getNumber());
        }

        IMeeting meeting = new Meeting(currentUser, eBoks, largestNum+1);
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<IMeeting> getMeetings() {
        return meetings;
    }

    /**
     * Set the meetings.
     * @param meetings any set with meetings
     */
    public void setMeetings(Set<IMeeting> meetings) {
        this.meetings = meetings;
    }
}
