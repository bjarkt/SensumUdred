package UI.components.landing_page;

import UI.components.Component;
import UI.components.IEventListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LandingPageController extends Component implements ILandingPage {

    private List<IEventListener<?>> onContinueSubscribers = new ArrayList<>();

    public LandingPageController(){super("landing_page.fxml");}


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void onLogIn(IEventListener<?> listener) {
        onContinueSubscribers.add(listener);
    }

    @FXML
    void enterApp(ActionEvent event) {
        onContinueSubscribers.forEach(listener -> listener.onAction(null));
    }


}
