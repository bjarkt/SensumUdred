package BLL.summon_citizen_to_meeting;

import BLL.ACQ.IUser;

import java.util.GregorianCalendar;
import java.util.Set;

public interface IMeeting {
    boolean sendMessage();
    void addParticipant(IUser participant);
    void addParticipant(IUser ...participant);
    Set<IUser> getParticipants();
    void removeParticipant(IUser participant);
    String getInformation();
    void setInformation(String information);
    GregorianCalendar getMeetingDate();
    void setMeetingDate(GregorianCalendar meetingDate);
    void setMeetingDate(int year, int month, int day, int hour, int minute);
}
