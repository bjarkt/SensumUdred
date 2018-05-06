package BLL.meeting;

import BLL.ACQ.IEBoks;
import BLL.ACQ.IUser;

import java.util.*;

class Meeting implements IMeeting {
    private IUser creator;
    private Set<IUser> participants;
    private String information;
    private GregorianCalendar meetingDate;
    private IEBoks eBoks;
    private boolean hasMeetingDateBeenSet;
    private boolean isCancelled;

    Meeting(IUser creator) {
        participants = new HashSet<>();
        meetingDate = new GregorianCalendar();
        eBoks = new EBoksImpl();
        hasMeetingDateBeenSet = false;
        isCancelled = false;
        this.creator = creator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean sendMeetingMessage() {
        if (!hasMeetingDateBeenSet || participants.size() == 0) {
            // error, missing information
            return false;
        }
        return eBoks.sendMeetingMessage(participants, meetingDate, information);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean cancelMeeting(IUser currentUser) {
        boolean meetingSuccessfullyCancelled = false;
        if (currentUser.equals(creator) || isUserParticipating(currentUser)) {
            meetingSuccessfullyCancelled = eBoks.sendCancelMeetingMessage(participants, meetingDate, this.information);
            if (meetingSuccessfullyCancelled) {
                String newInformation = "VIGTIGT: Dette møde er blevet aflyst!" + System.lineSeparator() + System.lineSeparator() + information;
                this.setInformation(newInformation);
                this.isCancelled = true;
            }
        }
        return meetingSuccessfullyCancelled;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addParticipant(IUser ...participant) {
        participants.addAll(Arrays.asList(participant));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<IUser> getParticipants() {
        return participants;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isUserParticipating(IUser participant) {
        return participants.contains(participant);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeParticipant(IUser stakeholder) {
        participants.remove(stakeholder);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInformation() {
        return information;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInformation(String information) {
        this.information = information;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date getMeetingDate() {
        return new Date(meetingDate.getTimeInMillis());
    }

    /**
     * {@inheritDoc}
     */
    @Override
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