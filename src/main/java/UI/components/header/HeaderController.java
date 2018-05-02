package UI.components.header;

import UI.components.Component;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class HeaderController extends Component implements IHeader{


    @FXML
    private Button hamburger;

    @FXML
    private Rectangle hamburgerRectangel1;

    @FXML
    private Rectangle hamburgerRectangel2;

    @FXML
    private Rectangle hamburgerRectangel3;

    @FXML
    void hover(MouseEvent event) {

    }

    @FXML
    void leave(MouseEvent event) {

    }

    @FXML
    void openNav(ActionEvent event) {

    }

    public HeaderController(){super("header.fxml");}


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
