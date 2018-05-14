package UI.components.vertical_menu;

import UI.components.Component;
import UI.components.IEventListener;
import UI.components.IAccessRequirement;
import UI.components.SecureComponent;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.*;

public class VerticalMenuController extends Component implements IVerticalMenu {

    private IAccessRequirement securityLevel;

    private List<IEventListener<?>> onMyElucidationSubscribers = new ArrayList<>();
    private List<IEventListener<?>> onLogSubscribers = new ArrayList<>();
    private List<IEventListener<?>> onUserManagementSubscribers = new ArrayList<>();

    private List<SecureComponent> buttons;

    @FXML
    private JFXButton myElucidationsButton;

    @FXML
    private JFXButton logButton;

    @FXML
    private JFXButton userManagementButton;

    @FXML
    private AnchorPane verticalMenuWrapper;

    @FXML
    private GridPane verticalMenuGrid;

    public VerticalMenuController(IAccessRequirement securityLevel) {
        super("vertical_menu.fxml");
        this.securityLevel = securityLevel;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttons = new ArrayList<>();
        buttons.add(new SecureComponent(myElucidationsButton,1));
        buttons.add(new SecureComponent(logButton,1));
        buttons.add(new SecureComponent(userManagementButton,1));

        for (SecureComponent button : buttons) {
            if(securityLevel.getUserAccessLevel() < button.getSecurityLevel()) {
                int row = verticalMenuGrid.getRowIndex(button.getComponent());
                verticalMenuGrid.getChildren().remove(button.getComponent());
                deleteRow(row);
            }
        }
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
    void viewUserManagement(ActionEvent event) {
        setUserManagementButtonActive();
        onUserManagementSubscribers.forEach(listener -> listener.onAction(null));
    }

    private void setActiveButtonStyle(JFXButton button){
        for (SecureComponent buttonInList : buttons) {
            buttonInList.getComponent().getStyleClass().remove("active");
        }
        button.getStyleClass().add("active");
    }

    /**
     * Deletes row and moves nodes.
     * @param row row to be deleted.
     */
    private void deleteRow(int row){
        Set<Node> deleteNodes = new HashSet<>();
        for (Node child : verticalMenuGrid.getChildren()) {
            Integer rowIndex = GridPane.getRowIndex(child);
            int r = rowIndex == null ? 0 : rowIndex;
            if (r > row) {
                GridPane.setRowIndex(child, r-1);
            } else if (r == row) {
                deleteNodes.add(child);
            }
        }
        verticalMenuGrid.getChildren().removeAll(deleteNodes);
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

    @Override
    public List<SecureComponent> getButtons() {
        return buttons;
    }
}
