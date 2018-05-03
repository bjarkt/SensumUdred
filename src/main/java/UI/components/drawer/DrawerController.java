package UI.components.drawer;

import UI.components.Component;
import UI.components.IEventListener;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DrawerController extends Component implements IDrawer {

    public DrawerController() {
        super("drawer.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
