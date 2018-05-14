package UI.components.elucidation_view;

import UI.components.Component;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ElucidationViewController extends Component implements IElucidationView {

    private IElucidationViewRequire required;

    @FXML
    private AnchorPane elucidation_view_container;

    public ElucidationViewController() {
        super("elucidation_view.fxml", "Elucidation_Name");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}
