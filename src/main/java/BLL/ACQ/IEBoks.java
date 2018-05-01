package BLL.ACQ;

import java.util.Collection;
import java.util.GregorianCalendar;

public interface IEBoks {
    boolean sendMessage(Collection<IUser> stakeholders, GregorianCalendar meetingDate, String info);
}
