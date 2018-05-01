package BLL.summon_citizen_to_meeting;

import BLL.ACQ.IEBoks;
import BLL.ACQ.IUser;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

public class Meeting implements IMeeting {
    private Set<IUser> participants;
    private String information;
    private GregorianCalendar meetingDate;
    private IEBoks eBoks;
    private boolean hasMeetingDateBeenSet;

    public Meeting() {
        participants = new HashSet<>();
        meetingDate = new GregorianCalendar() {
            @Override
            public String toString() {
                return get(GregorianCalendar.YEAR) + " " + get(GregorianCalendar.MONTH) + " "
                        + get(GregorianCalendar.DAY_OF_MONTH) + " " + get(GregorianCalendar.HOUR) + " "
                        + get(GregorianCalendar.MINUTE) + " ";
            }
        };
        eBoks = new EBoksImpl();
        hasMeetingDateBeenSet = false;
    }

    public boolean sendMessage() {
        if (!hasMeetingDateBeenSet || participants.size() == 0) {
            // error, missing information
            return false;
        }
        return eBoks.sendMessage(participants, meetingDate, information);
    }

    public void addParticipant(IUser participant) {
        participants.add(participant);
    }

    public void addParticipant(IUser ...participant) {
        for (IUser stakeholder : participant) {
            this.addParticipant(stakeholder);
        }
    }

    public Set<IUser> getParticipants() {
        return participants;
    }

    public void removeParticipant(IUser stakeholder) {
        participants.remove(stakeholder);
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public GregorianCalendar getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(GregorianCalendar meetingDate) {
        this.setMeetingDate(meetingDate.get(GregorianCalendar.YEAR),
                meetingDate.get(GregorianCalendar.MONTH),
                meetingDate.get(GregorianCalendar.DAY_OF_MONTH),
                meetingDate.get(GregorianCalendar.HOUR),
                meetingDate.get(GregorianCalendar.MINUTE));
    }

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
                "\nmeetingDate=" + meetingDate +
                "\n}";
    }
}