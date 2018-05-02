package BLL.summon_citizen_to_meeting;


import BLL.ACQ.IEBoks;
import BLL.ACQ.IUser;

import java.util.Collection;
import java.util.GregorianCalendar;

public class EBoksImpl implements IEBoks {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean sendMessage(Collection<IUser> stakeholders, GregorianCalendar meetingDate, String info) {
        return true;
    }
}
