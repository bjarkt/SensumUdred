package UI.components.vertical_menu;

import ACQ.IEventListener;
import UI.Secured;
import UI.components.Component;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class VerticalMenuController extends Component implements IVerticalMenu {

    private List<JFXButton> buttons;

    private List<IEventListener<?>> onMyElucidationSubscribers = new ArrayList<>();
    private List<IEventListener<?>> onLogSubscribers = new ArrayList<>();
    private List<IEventListener<?>> onUserManagementSubscribers = new ArrayList<>();
    private List<IEventListener<?>> onMyMeetingsSubscribers = new ArrayList<>();

    @Secured("getMyElucidations")
    @FXML
    private JFXButton myElucidationsButton;

    @Secured("getLogGetterService")
    @FXML
    private JFXButton logButton;

    @FXML
    private JFXButton myMeetings;

    @Secured("getAdminService")
    @FXML
    private JFXButton userManagementButton;

    @FXML
    private AnchorPane verticalMenuWrapper;

    @FXML
    private GridPane verticalMenuGrid;

    public VerticalMenuController() {
        super("vertical_menu.fxml");
        buttons = new ArrayList<>();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttons.add(logButton);
        buttons.add(myElucidationsButton);
        buttons.add(userManagementButton);
        buttons.add(myMeetings);
    }

    @Override
    public void onMyElucidationsClick(IEventListener<?> listener) {
        onMyElucidationSubscribers.add(listener);
    }

    @Override
    public void onLogClick(IEventListener<?> listener) {
        onLogSubscribers.add(listener);
    }

    @Override
    public void onMyMeetingsClick(IEventListener<?> listener) {
        onMyMeetingsSubscribers.add(listener);
    }

    @Override
    public void onUserManagement(IEventListener<?> listener) {
        onUserManagementSubscribers.add(listener);
    }

    @FXML
    void viewLog(ActionEvent event) {
        setLogButtonActive();
        onLogSubscribers.forEach(listener -> listener.onAction(null));
    }

    @FXML
    void viewMyElucidations(ActionEvent event) {
        setMyElucidationsButtonActive();
        onMyElucidationSubscribers.forEach(listener -> listener.onAction(null));
    }


    @FXML
    void viewMyMeetings(ActionEvent event) {
        setMeetingsButtonActive();
        onMyMeetingsSubscribers.forEach(listener -> listener.onAction(null));
    }

    @FXML
    void viewUserManagement(ActionEvent event) {
        setUserManagementButtonActive();
        onUserManagementSubscribers.forEach(listener -> listener.onAction(null));
    }

    private void setActiveButtonStyle(JFXButton button){
        for (JFXButton buttonInList : buttons) {
            buttonInList.getStyleClass().remove("active");
        }
        button.getStyleClass().add("active");
    }

    @Override
    public void setMyElucidationsButtonActive() {
        setActiveButtonStyle(myElucidationsButton);
    }

    @Override
    public void setLogButtonActive() {
        setActiveButtonStyle(logButton);
    }

    @Override
    public void setUserManagementButtonActive() {
        setActiveButtonStyle(userManagementButton);
    }


    public void setMeetingsButtonActive() {
        setActiveButtonStyle(myMeetings);
    }

}
