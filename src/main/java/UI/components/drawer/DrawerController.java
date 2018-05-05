package UI.components.drawer;

import UI.components.Component;
import UI.components.ComponentLoader;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class DrawerController extends Component implements IDrawer {

    private IDrawerRequire required;

    private boolean isVisible;

    @FXML
    private JFXDrawer drawer;

    public DrawerController() {
        super("drawer.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        StackPane drawerPane = new StackPane();
        drawerPane.getChildren().addAll(new JFXButton("Drawer button"));
        drawer.setSidePane(drawerPane);
        drawer.setOverLayVisible(true);
        drawer.setResizableOnDrag(false);
        drawer.onDrawerClosedProperty().set(event -> toggleVisibility());
    }

    @Override
    public void toggleVisibility() {
        if(isVisible) {
            isVisible = false;
            drawer.close();
            ComponentLoader.removeComponent(this);
        }
        else {
            required.getParent().getChildren().add(this.getView());
            required.getParent().setTopAnchor(this.getView(), .0);
            required.getParent().setLeftAnchor(this.getView(), .0);
            required.getParent().setRightAnchor(this.getView(), .0);
            required.getParent().setBottomAnchor(this.getView(), .0);
            isVisible = true;
            drawer.open();
        }
    }

    @Override
    public void setRequired(IDrawerRequire required) {
        this.required = required;
    }
}
