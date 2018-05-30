package UI.components.meetingPopUp;

import ACQ.IEventListener;
import ACQ.IUser;
import UI.components.IComponent;
import UI.components.dropdown_search.IDropdownSearch;

import java.util.Set;

public interface IMeetingPopUp extends IComponent {
    void setRequired(IMeetingPopUpRequire required);

    void show(String subject, String message);

    void close();

    IDropdownSearch<IUser> getDropdownSearch();

    void onDone(IEventListener<?> listener);

    Set<IUser> getChosenAttendees();

    String getMeetingInformation();

    int[] getMeetingDate();

}
