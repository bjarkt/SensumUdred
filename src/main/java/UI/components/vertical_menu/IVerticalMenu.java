package UI.components.vertical_menu;

import ACQ.IEventListener;
import UI.components.IComponent;

public interface IVerticalMenu extends IComponent {

    void onMyElucidationsClick(IEventListener<?> listener);

    void onLogClick(IEventListener<?> listener);

    void onMyMeetingsClick(IEventListener<?> listener);

    void onUserManagement(IEventListener<?> listener);

	void setMyElucidationsButtonActive();

	void setLogButtonActive();

    void setUserManagementButtonActive();
}
