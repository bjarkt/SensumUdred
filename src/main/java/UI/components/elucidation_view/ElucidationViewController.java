package UI.components.elucidation_view;

import ACQ.*;
import UI.Secured;
import UI.components.Component;
import UI.components.dropdown_search.DropdownSearchController;
import UI.components.dropdown_search.IDropdownSearch;
import UI.components.dropdown_search.IDropdownSearchRequire;
import UI.components.elucidation_view.granting.GrantingController;
import UI.components.elucidation_view.granting.IGranting;
import UI.components.elucidation_view.textfield_with_checkbox.ITextFieldWithCheckbox;
import UI.components.elucidation_view.textfield_with_checkbox.TextFieldWithCheckboxController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXListCell;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class ElucidationViewController extends Component implements IElucidationView {

    private List<IEventListener<?>> leaveEludicationSubscribers = new ArrayList<>();
    private List<IEventListener<String>> saveCaseDescriptionSubscribers = new ArrayList<>();
    private List<IEventListener<String>> addNewOfferSubscribers = new ArrayList<>();
    private List<IEventListener<String[]>> deleteOfferSubscribers = new ArrayList<>();

    private IElucidationViewRequire required;

    private boolean isMobile;

    @Secured("addCaseworkerToCase")
    private IDropdownSearch<IProfile> caseWorkerSearcher;

    @FXML
    private VBox caseWorkerContainer;

    //region offers_section

    private ObservableSet<ITextFieldWithCheckbox> listOfChosenOffers = FXCollections.observableSet();

    @FXML
    private VBox offersContainer;

    @FXML
    private VBox caseOffersWrapper;


    @FXML
    private JFXButton editOffersbutton;

    @FXML
    private JFXButton editCaseOffersButton;

    @FXML
    private JFXButton deleteCaseOfferButton;

    @FXML
    void addCaseOffer(ActionEvent event) {
        ITextFieldWithCheckbox offer = new TextFieldWithCheckboxController();
        offer.onTextFieldSelected(data -> {
            if(data.isSelected()) listOfChosenOffers.add(data);
            else listOfChosenOffers.remove(data);
        });
        caseOffersWrapper.getChildren().add(offer.getView());
    }

    @FXML
    void deleteCaseOffers(ActionEvent event) {
        for (ITextFieldWithCheckbox textFieldWithCheckbox : listOfChosenOffers) {
            caseOffersWrapper.getChildren().remove(textFieldWithCheckbox.getView());
        }
        listOfChosenOffers.clear();
    }

    private void setupOffersSection(){
        listOfChosenOffers.addListener(new SetChangeListener<ITextFieldWithCheckbox>(){
            @Override
            public void onChanged(Change<? extends ITextFieldWithCheckbox> change) {
                if(listOfChosenOffers.size() > 0){
                    deleteCaseOfferButton.setDisable(false);
                    deleteCaseOfferButton.setText("Slet " + listOfChosenOffers.size() + " tilbud");
                } else{
                    deleteCaseOfferButton.setDisable(true);
                    deleteCaseOfferButton.setText("Ingen tilbud valgt");
                }
            }
        });
    }
    //endregion


    //region setup grantings section

    private ObservableSet<IGranting> listOfChosenGrantings = FXCollections.observableSet();

    @FXML
    private VBox grantingsContainer;

    @FXML
    private VBox grantingWrapper;

    @FXML
    private JFXButton addCaseGrantingsbutton;

    @FXML
    private JFXButton deleteGrantingsButton;

    @FXML
    void addCaseGranting(ActionEvent event) {
        IGranting granting = new GrantingController();
        granting.onGrantingSelected(data -> {
            if(data.isSelected()) listOfChosenGrantings.add(data);
            else listOfChosenGrantings.remove(data);
        });
        grantingWrapper.getChildren().add(granting.getView());
    }

    @FXML
    void deleteGranting(ActionEvent event) {
        for (IGranting granting : listOfChosenGrantings) {
            grantingWrapper.getChildren().remove(granting.getView());
        }
        listOfChosenGrantings.clear();
    }

    private void setupUpGrantingsSection(){
        listOfChosenGrantings.addListener(new SetChangeListener<IGranting>() {
            @Override
            public void onChanged(Change<? extends IGranting> change) {
                if(listOfChosenGrantings.size() > 0){
                    deleteGrantingsButton.setDisable(false);
                    deleteGrantingsButton.setText("Slet " + listOfChosenGrantings.size() + " ydelse");
                    if(listOfChosenGrantings.size() > 1) deleteGrantingsButton.setText("Slet " + listOfChosenGrantings.size() + " ydelser");
                } else{
                    deleteGrantingsButton.setDisable(true);
                    deleteGrantingsButton.setText("Ingen ydelser valgt");
                }
            }
        });
    }

    //endregion




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

        // Set cell template for user searcher.
        caseWorkerSearcher = new DropdownSearchController<>(new IDropdownSearchRequire<IProfile>() {
            @Override
            public JFXListCell getCellFactory() {
                return new NameCheckboxCell(){};
            }
        });

        // Add user searcher to appropriate container.
        caseWorkerContainer.getChildren().add(caseWorkerSearcher.getView());


        caseWorkerSearcher.onDone(data -> {
            System.out.println("Recieved: ");
            for (IProfile profile: data) {
                System.out.println(profile.getUser().getName());
            }
        });

        // Setup different components.
        setupUpGrantingsSection();
        setupOffersSection();

    }

    @Override
    public void onCaseSaveDescription(IEventListener<String> listener) {
        saveCaseDescriptionSubscribers.add(listener);
    }

    @Override
    public void onAddNewOffer(IEventListener<String> listener) {
        addNewOfferSubscribers.add(listener);
    }

    @Override
    public void onDeleteOffers(IEventListener<String[]> listener) {
        deleteOfferSubscribers.add(listener);
    }

    @Override
    public void tickOffersList(String... offers) {

    }





    @FXML
    void leaveElucidation(ActionEvent event) {
        leaveEludicationSubscribers.forEach(listener -> listener.onAction(null));
    }


    @FXML
    void editCaseDescription(ActionEvent event) {
        if(editCaseDescriptionButton.getText().equals("Rediger")){
            caseDescriptionField.setEditable(true);
            caseDescriptionField.requestFocus();
            caseDescriptionField.getStyleClass().add("editing");
            editCaseDescriptionButton.setText("Gem");
            editCaseDescriptionButton.getStyleClass().add("editing");
        } else if(editCaseDescriptionButton.getText().equals("Gem")){
            caseDescriptionField.setEditable(false);
            editCaseDescriptionButton.setText("Rediger");
            caseDescriptionField.getStyleClass().remove("editing");
            editCaseDescriptionButton.getStyleClass().remove("editing");
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

