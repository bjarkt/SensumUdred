package UI.components.drawer;

import UI.components.vertical_menu.IVerticalMenu;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public interface IDrawerRequire {

    AnchorPane getParent();

    Parent getContent();

}
