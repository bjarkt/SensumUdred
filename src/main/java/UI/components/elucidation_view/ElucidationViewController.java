package UI.components.elucidation_view;

import UI.components.Component;
import UI.components.IEventListener;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class ElucidationViewController extends Component implements IElucidationView {

    private List<IEventListener<?>> leaveEludicationSubscribers = new ArrayList<>();
    private List<IEventListener<String>> saveCaseDescriptionSubscribers = new ArrayList<>();

    private IElucidationViewRequire required;

    private boolean isMobile;

    @FXML
    private AnchorPane elucidation_view_container;

    @FXML
    private TextArea caseDescriptionField;

    @FXML
    private JFXButton editCaseDescriptionButton;

    @FXML
    private VBox elucidationView_contentWrapper;

    @FXML
    private ScrollPane elucidationView_contentScrollpane;

    @FXML
    private FlowPane elucidationView_contentFlowpane;

    @FXML
    private HBox elucidationView_horizontalLayout;

    @FXML
    private VBox elucidationView_verticalLayout;

    @FXML
    private VBox elucidationView_horizontalLayout_left;

    @FXML
    private VBox elucidationView_horizontalLayout_right;



    public ElucidationViewController() {
        super("elucidation_view.fxml", "Elucidation_Name");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        elucidation_view_container.widthProperty().addListener((observable, oldValue, newValue) -> {

            if(isMobile && newValue.doubleValue() > 800){
                isMobile = false;
                elucidationView_horizontalLayout.getChildren().addAll(elucidationView_horizontalLayout_left, elucidationView_horizontalLayout_right);

            } else if(!isMobile && newValue.doubleValue() < 800){
                isMobile = true;
                elucidationView_verticalLayout.getChildren().addAll(elucidationView_horizontalLayout_left, elucidationView_horizontalLayout_right);

            }
        });

    }

    @FXML
    void leaveElucidation(ActionEvent event) {
        leaveEludicationSubscribers.forEach(listener -> listener.onAction(null));
    }

    @Override
    public void onCaseSaveDescription(IEventListener<String> listener) {
        saveCaseDescriptionSubscribers.add(listener);
    }

    @FXML
    void editCaseDescription(ActionEvent event) {
        if(editCaseDescriptionButton.getText().equals("Rediger")){
            caseDescriptionField.setEditable(true);
            editCaseDescriptionButton.setText("Gem");
        } else if(editCaseDescriptionButton.getText().equals("Gem")){
            caseDescriptionField.setEditable(false);
            editCaseDescriptionButton.setText("Rediger");
            saveCaseDescriptionSubscribers.forEach(listener -> listener.onAction(caseDescriptionField.getText()));
        }

    }

    @Override
    public void onLeaveElucidation(IEventListener<?> listener) {
        leaveEludicationSubscribers.add(listener);
    }
}
