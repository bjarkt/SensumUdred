package UI.components.meetingPopUp;

import ACQ.IEventListener;
import UI.components.IComponent;

public interface IMeetingPopUp extends IComponent {
    void setRequired(IMeetingPopUpRequire required);

    void show(String subject, String message);

    void close();
}
