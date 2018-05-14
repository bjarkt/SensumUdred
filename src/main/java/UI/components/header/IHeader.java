package UI.components.header;

import UI.components.IComponent;
import UI.components.IEventListener;
import javafx.beans.property.StringProperty;

public interface IHeader extends IComponent {

    void setHeaderTitle(String title);

    void onMenuClick(IEventListener<?> listener);

    void onMessagesClick(IEventListener<?> listener);

    void onProfileClick(IEventListener<?> listener);

    StringProperty getTitleProperty();

    void enableHamburger();

    void disableHamburger();

}
