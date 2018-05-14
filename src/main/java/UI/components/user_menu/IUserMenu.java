package UI.components.user_menu;

import UI.components.IComponent;
import UI.components.IEventListener;
import javafx.scene.layout.Pane;

public interface IUserMenu extends IComponent {

    void setUsersName(String usersName);

    void setUserID(String userID);

    void onLogOut(IEventListener<?> listener);

}
