package UI.components.user_menu;

import ACQ.IEventListener;
import UI.components.Component;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserMenuController extends Component implements IUserMenu {

    private List<IEventListener<?>> logOutSubscribers = new ArrayList<>();

    @FXML
    private AnchorPane userMenuWrapper;

    @FXML
    private Label usersName;

    @FXML
    private Label username;

    public UserMenuController() {
        super("user_menu.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void setUsersName(String usersName) {
        this.usersName.setText(usersName);
    }

    @Override
    public void setUserID(String userID) {
        this.username.setText(userID);
    }

    @Override
    public void onLogOut(IEventListener<?> listener) {
        logOutSubscribers.add(listener);
    }

    @FXML
    void logOut(ActionEvent event) {
        logOutSubscribers.forEach(listener -> listener.onAction(null));
    }
}
