package UI.components.elucidation_view;

import UI.components.Component;
import UI.components.IEventListener;
import UI.components.dropdown_search.DropdownSearchController;
import UI.components.dropdown_search.IDropdownSearch;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class ElucidationViewController extends Component implements IElucidationView {

    private List<IEventListener<?>> leaveEludicationSubscribers = new ArrayList<>();
    private List<IEventListener<String>> saveCaseDescriptionSubscribers = new ArrayList<>();
    private List<IEventListener<String>> addNewOfferSubscribers = new ArrayList<>();

    private IElucidationViewRequire required;

    private boolean isMobile;

    private IDropdownSearch caseWorkerSearcher;

    @FXML
    private VBox caseWorkerContainer;



    @FXML
    private AnchorPane elucidation_view_container;

    @FXML
    private VBox elucidationView_contentWrapper;

    @FXML
    private ScrollPane elucidationView_contentScrollpane;

    @FXML
    private HBox elucidationView_horizontalLayout;

    @FXML
    private VBox elucidationView_horizontalLayout_left;

    @FXML
    private JFXButton editCaseDescriptionButton;

    @FXML
    private TextArea caseDescriptionField;

    @FXML
    private VBox offersContainer;

    @FXML
    private JFXButton editCaseOffersButton;

    @FXML
    private JFXButton editCaseOfferingsbutton;

    @FXML
    private JFXButton editCaseCitizenAgreementButton;

    @FXML
    private JFXButton editCaseCitizenMunicipalityButton;

    @FXML
    private JFXButton editCaseSpecialCircumstancesButton;

    @FXML
    private VBox elucidationView_horizontalLayout_right;

    @FXML
    private VBox elucidationView_verticalLayout;




    public ElucidationViewController() {
        super("elucidation_view.fxml", "Elucidation_Name");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Makes view responsive
        elucidation_view_container.widthProperty().addListener((observable, oldValue, newValue) -> {
            if(isMobile && newValue.doubleValue() > 800){
                isMobile = false;
                elucidationView_horizontalLayout.getChildren().addAll(elucidationView_horizontalLayout_left, elucidationView_horizontalLayout_right);
            } else if(!isMobile && newValue.doubleValue() < 800){
                isMobile = true;
                elucidationView_verticalLayout.getChildren().addAll(elucidationView_horizontalLayout_left, elucidationView_horizontalLayout_right);
            }
        });

        caseWorkerSearcher = new DropdownSearchController();
        caseWorkerContainer.getChildren().add(caseWorkerSearcher.getView());

    }

    @FXML
    void leaveElucidation(ActionEvent event) {
        leaveEludicationSubscribers.forEach(listener -> listener.onAction(null));
    }

    @Override
    public void onCaseSaveDescription(IEventListener<String> listener) {
        saveCaseDescriptionSubscribers.add(listener);
    }

    @Override
    public void onAddNewOffer(IEventListener<String> listener) {
        addNewOfferSubscribers.add(listener);
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
    @FXML
    void editAdressCityOnClick(MouseEvent event) {

    }

    @FXML
    void editAdressStreetOnClick(MouseEvent event) {

    }

    @FXML
    void editCPROnClick(MouseEvent event) {

    }



    @FXML
    void editCitizenAgreementButton(ActionEvent event) {

    }

    @FXML
    void editCitizenMunicipalityButton(ActionEvent event) {

    }

    @FXML
    void editCivilStatusOnClick(MouseEvent event) {

    }

    @FXML
    void editContactDetailsCellOnClick(MouseEvent event) {

    }

    @FXML
    void editContactDetailsPhoneOnClick(MouseEvent event) {

    }

    @FXML
    void editNameOnClick(MouseEvent event) {

    }

    @FXML
    void editOfferingsbutton(ActionEvent event) {

    }

    @FXML
    void editOffersButton(ActionEvent event) {
        TextField textArea = new TextField();
        textArea.getStyleClass().add("elucidationView_simpleInputField");
        offersContainer.getChildren().add(textArea);
        textArea.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.ENTER)){
                    addNewOfferSubscribers.forEach(listener -> listener.onAction(textArea.getText()));
                    editCaseOffersButton.requestFocus();
                }
            }
        });
    }

    @FXML
    void editRegistrationDateOnClick(MouseEvent event) {

    }

    @FXML
    void editSpecialCircumstancesButton(ActionEvent event) {

    }



    @FXML
    void saveAdressCityOnEnter(MouseEvent event) {

    }

    @FXML
    void saveAdressStreetOnEnter(MouseEvent event) {

    }

    @FXML
    void saveCPROnEnter(MouseEvent event) {

    }

    @FXML
    void saveCivilStatusOnEnter(MouseEvent event) {

    }

    @FXML
    void saveContactDetailsCellOnEnter(MouseEvent event) {

    }

    @FXML
    void saveContactDetailsPhoneOnEnter(MouseEvent event) {

    }

    @FXML
    void saveNameOnEnter(MouseEvent event) {

    }

    @FXML
    void saveRegistrationDateOnEnter(MouseEvent event) {

    }

    @Override
    public void onLeaveElucidation(IEventListener<?> listener) {
        leaveEludicationSubscribers.add(listener);
    }
}

