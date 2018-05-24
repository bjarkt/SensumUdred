package UI.components.meetingPopUp;

import ACQ.IEventListener;
import ACQ.IProfile;
import UI.components.IComponent;
import UI.components.dropdown_search.IDropdownSearch;

public interface IMeetingPopUp extends IComponent {
    void setRequired(IMeetingPopUpRequire required);

    void show(String subject, String message);

    void close();

    IDropdownSearch<IProfile> getDropdownSearch();

}
