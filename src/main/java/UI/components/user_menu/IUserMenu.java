package UI.components.user_menu;

import UI.components.IComponent;
import ACQ.IEventListener;

public interface IUserMenu extends IComponent {

    void setUsersName(String usersName);

    void setUserID(String userID);

    void onLogOut(IEventListener<?> listener);

}
