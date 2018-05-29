package UI.components.user_menu;

import ACQ.IEventListener;
import UI.components.IComponent;

public interface IUserMenu extends IComponent {

    void setUsersName(String usersName);

    void setUserID(String userID);

    void onLogOut(IEventListener<?> listener);

}
