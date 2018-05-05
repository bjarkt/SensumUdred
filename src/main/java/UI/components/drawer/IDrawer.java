package UI.components.drawer;

import UI.components.IComponent;
import UI.components.IEventListener;
import javafx.scene.layout.Pane;

public interface IDrawer extends IComponent {

    void onCrossClick(IEventListener<?> listener);

    void setRequired(IDrawerRequire required);

    void open();

    void close();
}
