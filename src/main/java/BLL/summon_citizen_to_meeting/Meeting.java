package BLL.summon_citizen_to_meeting;

import BLL.ACQ.IEBoks;
import BLL.ACQ.IUser;

import java.util.*;

class Meeting implements IMeeting {
    private Set<IUser> participants;
    private String information;
    private GregorianCalendar meetingDate;
    private IEBoks eBoks;
    private boolean hasMeetingDateBeenSet;

    Meeting() {
        participants = new HashSet<>();
        meetingDate = new GregorianCalendar();
        eBoks = new EBoksImpl();
        hasMeetingDateBeenSet = false;
    }

    /**
     * {@inheritDoc}
     */
    public boolean sendMessage() {
        if (!hasMeetingDateBeenSet || participants.size() == 0) {
            // error, missing information
            return false;
        }
        return eBoks.sendMessage(participants, meetingDate, information);
    }

    /**
     * {@inheritDoc}
     */
    public void addParticipant(IUser ...participant) {
        participants.addAll(Arrays.asList(participant));
    }

    /**
     * {@inheritDoc}
     */
    public Set<IUser> getParticipants() {
        return participants;
    }

    /**
     * {@inheritDoc}
     */
    public void removeParticipant(IUser stakeholder) {
        participants.remove(stakeholder);
    }

    /**
     * {@inheritDoc}
     */
    public String getInformation() {
        return information;
    }

    /**
     * {@inheritDoc}
     */
    public void setInformation(String information) {
        this.information = information;
    }

    /**
     * {@inheritDoc}
     */
    public Date getMeetingDate() {
        return new Date(meetingDate.getTimeInMillis());
    }

    /**
     * {@inheritDoc}
     */
    public void setMeetingDate(int year, int month, int day, int hour, int minute) {
        meetingDate.set(GregorianCalendar.YEAR, year);
        meetingDate.set(GregorianCalendar.MONTH, month);
        meetingDate.set(GregorianCalendar.DAY_OF_MONTH, day);
        meetingDate.set(GregorianCalendar.HOUR, hour);
        meetingDate.set(GregorianCalendar.MINUTE, minute);
        meetingDate.set(GregorianCalendar.SECOND, 0);
        this.hasMeetingDateBeenSet = true;
    }

    @Override
    public String toString() {
        return "Meeting{\n" +
                "information='" + information + '\'' +
                "\nparticipants=" + participants +
                "\nmeetingDate=" + getMeetingDate() +
                "\n}";
    }
}