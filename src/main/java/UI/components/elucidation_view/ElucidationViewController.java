package UI.components.elucidation_view;

import UI.components.Component;
import UI.components.IEventListener;
import com.jfoenix.controls.JFXListView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ElucidationViewController extends Component implements IElucidationView {

    private List<IEventListener<?>> leaveEludicationSubscribers = new ArrayList<>();

    private IElucidationViewRequire required;

    @FXML
    private AnchorPane elucidation_view_container;

    public ElucidationViewController() {
        super("elucidation_view.fxml", "Elucidation_Name");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    @FXML
    void leaveElucidation(ActionEvent event) {
        leaveEludicationSubscribers.forEach(listener -> listener.onAction(null));
    }

    @Override
    public void onLeaveElucidation(IEventListener<?> listener) {
        leaveEludicationSubscribers.add(listener);
    }
}
