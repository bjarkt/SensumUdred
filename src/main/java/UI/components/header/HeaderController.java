package UI.components.header;

import UI.components.Component;
import UI.components.IEventListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HeaderController extends Component implements IHeader{

    private List<IEventListener<?>> menuSubscribers = new ArrayList<>();


    @FXML
    private Button hamburger;

    @FXML
    private Rectangle hamburgerRectangel1;

    @FXML
    private Rectangle hamburgerRectangel2;

    @FXML
    private Rectangle hamburgerRectangel3;


    public HeaderController(){super("header.fxml");}

    @Override
    public void onMenuClick(IEventListener<?> listener) {
        menuSubscribers.add(listener);
    }

    @FXML
    void hover(MouseEvent event) {

    }

    @FXML
    void leave(MouseEvent event) {

    }

    @FXML
    void openNav(ActionEvent event) {
        menuSubscribers.forEach(listener -> listener.onAction(null));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
