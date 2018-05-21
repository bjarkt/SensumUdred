package UI.components.popUp;

import UI.components.IComponent;

public interface IPopup extends IComponent {
    void setRequired(IPopUpRequire required);

    void show(String subject, String message);

    void close();
}
