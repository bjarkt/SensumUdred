package UI.components.popUp;

import UI.components.IComponent;
import UI.components.IEventListener;
import UI.components.popUp.IPopUpRequire;
import javafx.scene.layout.Pane;

public interface IPopup extends IComponent {
    void setRequired(IPopUpRequire required);

    void show(String subject, String message);

    void close();
}
