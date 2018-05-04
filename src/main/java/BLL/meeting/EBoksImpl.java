package BLL.meeting;


import BLL.ACQ.IEBoks;
import BLL.ACQ.IUser;

import java.util.Collection;
import java.util.GregorianCalendar;

public class EBoksImpl implements IEBoks {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean sendMeetingMessage(Collection<IUser> stakeholders, GregorianCalendar meetingDate, String info) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean sendCancelMeetingMessage(Collection<IUser> participants, GregorianCalendar meetingDate, String info) {
        return true;
    }
}
