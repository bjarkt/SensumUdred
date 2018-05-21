package ACQ;

import java.util.Date;
import java.util.Set;

public interface IMeeting {
    /**
     * Send a message with the information contained in the meeting
     * @return true if message was sent successfully
     */
    boolean sendMeetingMessage();

    /**
     * Cancel a meeting, and notify the participants that the meeting has been cancelled
     * @return true if the meeting was cancelled successfully
     */
    boolean cancelMeeting(IUser currentUser);

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
     * Is the participant participating in this meeting?
     * @param participant the user who may be participating in the meeting.
     * @return true if the participant is participating in the meeting.
     */
    boolean isUserParticipating(IUser participant);

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
     * Is the meeting cancelled?
     * @return true if the meeting has been cancelled
     */
    boolean isCancelled();

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

    /**
     * Get the id of the meeting
     * @return id of meeting
     */
    int getId();

    /**
     * Get the user that created the meeting
     * @return creator of meeting
     */
    IUser getCreator();
}
