package UI.components.drawer;

import UI.components.IComponent;

public interface IDrawer extends IComponent {
    void setRequired(IDrawerRequire required);

    void open();

    void close();

    void setContent();

    void clearContent();
}
