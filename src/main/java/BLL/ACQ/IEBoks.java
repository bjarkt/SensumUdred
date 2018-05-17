package BLL.ACQ;

import java.util.Collection;
import java.util.GregorianCalendar;

public interface IEBoks {

    /**
     * Send a message to the participants about a meeting
     * @param participants who should participate in the meeting
     * @param meetingDate when is the meeting
     * @param info text information about the meeting
     * @return true if the meeting message was sent successfully
     */
    boolean sendMeetingMessage(Collection<IUser> participants, GregorianCalendar meetingDate, String info);


    /**
     * Send a message to participants
     * @param participants who should receive a message
     * @param info what text should the message contain
     * @return true if the message was sent successfully
     */
    boolean sendMessage(Collection<IUser> participants, String info);

    /**
     * Send a message to the participants about an existing meeting, that has been cancelled
     * @param participants Who is participating in the meeting
     * @param meetingDate When was the meeting supposed to happen
     * @param info What information did the meeting have
     * @return true if the meeting cancel message was successfully sent
     */
    boolean sendCancelMeetingMessage(Collection<IUser> participants, GregorianCalendar meetingDate, String info);
}
