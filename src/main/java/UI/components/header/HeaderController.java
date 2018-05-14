package UI.components.header;

import UI.components.Component;
import UI.components.IEventListener;
import com.jfoenix.controls.JFXButton;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HeaderController extends Component implements IHeader{

    private List<IEventListener<?>> menuSubscribers = new ArrayList<>();
    private List<IEventListener<?>> profileSubscribers = new ArrayList<>();
    private List<IEventListener<?>> messagesSubscribers = new ArrayList<>();

    @FXML
    private Label title;

    @FXML
    private JFXButton hamburger;

    @FXML
    private JFXButton messages;

    @FXML
    private JFXButton userProfile;

    @FXML
    private Rectangle hamburgerRectangel1;

    @FXML
    private Rectangle hamburgerRectangel2;

    @FXML
    private Rectangle hamburgerRectangel3;


    public HeaderController(){super("header.fxml");}

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        disableHamburger();

    }

    @Override
    public void onMenuClick(IEventListener<?> listener) {
        menuSubscribers.add(listener);
    }

    @Override
    public void onMessagesClick(IEventListener<?> listener) {
        messagesSubscribers.add(listener);
    }

    @Override
    public void onProfileClick(IEventListener<?> listener) {
        profileSubscribers.add(listener);
    }

    @FXML
    void openNav(ActionEvent event) {
        menuSubscribers.forEach(listener -> listener.onAction(null));
    }

    @FXML
    void openMessages(ActionEvent event) {
        messagesSubscribers.forEach(listener -> listener.onAction(null));
    }

    @FXML
    void openProfile(ActionEvent event) {
        profileSubscribers.forEach(listener -> listener.onAction(null));
    }

    @Override
    public void setHeaderTitle(String title) {
        this.title.setText(title);
    }

    @Override
    public StringProperty getTitleProperty() {
        return this.title.textProperty();
    }

    @Override
    public void enableHamburger() {
        hamburger.setDisable(false);
        hamburger.setVisible(true);
    }

    @Override
    public void disableHamburger() {
        hamburger.setMaxWidth(1);
        hamburger.setDisable(true);
        hamburger.setVisible(false);
    }
}
