package UI.components.meetingPopUp;

import ACQ.IUser;
import UI.components.IComponent;
import UI.components.dropdown_search.IDropdownSearch;

public interface IMeetingPopUp extends IComponent {
    void setRequired(IMeetingPopUpRequire required);

    void show(String subject, String message);

    void close();

    IDropdownSearch<IUser> getDropdownSearch();

}
