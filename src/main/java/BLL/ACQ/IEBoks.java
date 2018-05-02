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
    boolean sendMessage(Collection<IUser> participants, GregorianCalendar meetingDate, String info);
}
