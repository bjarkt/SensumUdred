package UI.components;

import javafx.fxml.Initializable;
import javafx.scene.Parent;

public interface IComponent extends Initializable {
    /**
     * Returns an object of type {@link javafx.scene.Parent} that can be added to the scene graph.
     * @return     the loaded object hierarchy.
     */
    Parent getView();
}
