package BLL.summon_citizen_to_meeting;

import BLL.ACQ.IUser;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;

public interface IMeeting {
    /**
     * Send a message with the information contained in the meeting
     * @return true if message was sent successfully
     */
    boolean sendMessage();

    /**
     * Add one or more users to the meeting
     * @param participant one or more users
     */
    void addParticipant(IUser ...participant);

    /**
     * Get the users participating in the meeting
     * @return The users participating in the meeting
     */
    Set<IUser> getParticipants();

    /**
     * Remove a participant from the meeting
     * @param participant the user to be removed from the meeting
     */
    void removeParticipant(IUser participant);

    /**
     * Information about the meeting
     * @return meeting information
     */
    String getInformation();

    /**
     * Set meeting information
     * @param information meeting information
     */
    void setInformation(String information);

    /**
     * Get the date of the meeting
     * @return Get the date of the meeting
     */
    Date getMeetingDate();

    /**
     * Set the date of the meeting
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     */
    void setMeetingDate(int year, int month, int day, int hour, int minute);
}
