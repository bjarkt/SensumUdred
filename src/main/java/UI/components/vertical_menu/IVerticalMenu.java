package UI.components.vertical_menu;

import UI.components.IComponent;
import UI.components.IEventListener;

public interface IVerticalMenu extends IComponent {

    void onMyElucidationsClick(IEventListener<?> listener);

    void onLogClick(IEventListener<?> listener);

    void onUserManagement(IEventListener<?> listener);

	void setMyElucidationsButtonActive();

	void setLogButtonActive();

    void setUserManagementButtonActive();
}
