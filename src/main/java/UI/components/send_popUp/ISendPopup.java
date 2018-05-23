package UI.components.send_popUp;

import UI.components.IComponent;
import UI.components.send_popUp.ISendPopUpRequire;
import javafx.scene.layout.Pane;

public interface ISendPopup extends IComponent {
    void setRequired(ISendPopUpRequire required);

    void show(String subject, String message);

    void close();
}
