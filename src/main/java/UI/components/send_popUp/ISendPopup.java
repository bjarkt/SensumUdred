package UI.components.send_popUp;

import UI.components.IComponent;

public interface ISendPopup extends IComponent {
    void setRequired(ISendPopUpRequire required);

    void show();

    void close();
}
