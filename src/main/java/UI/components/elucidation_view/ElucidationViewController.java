package UI.components.elucidation_view;

import ACQ.*;
import UI.Secured;
import UI.components.Component;
import UI.components.dropdown_search.DropdownSearchController;
import UI.components.dropdown_search.IDropdownSearch;
import UI.components.dropdown_search.IDropdownSearchRequire;
import UI.components.dropdown_search.NameCheckboxCell;
import UI.components.elucidation_view.granting.GrantingController;
import UI.components.elucidation_view.granting.IGranting;
import UI.components.elucidation_view.textfield_with_checkbox.ITextFieldWithCheckbox;
import UI.components.elucidation_view.textfield_with_checkbox.TextFieldWithCheckboxController;
import UI.components.elucidation_view.theme.IThemeUI;
import UI.components.elucidation_view.theme.ThemeController;
import UI.components.elucidation_view.theme.ThemeData;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
    private List<IEventListener<String>> saveCitizenAgreementSubscribers = new ArrayList<>();
    private List<IEventListener<String>> saveCitizenMunicipalitySubscribers = new ArrayList<>();
    private List<IEventListener<String>> saveSpecialCircumstancesSubscribers = new ArrayList<>();
    private List<IEventListener<String>> editCitizenInformationSubscribers = new ArrayList<>();
    private List<IEventListener<?>> createMeetingSubscribers = new ArrayList<>();


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
    private TextField nameField;

    @FXML
    private TextField CPRField;

    @FXML
    private TextField cityField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField civilStatusField;

    @FXML
    private TextField phoneNumberField;
    @FXML
    private JFXButton editCaseCitizenInformation;

    @FXML
    private TextField cellphoneNumberField;

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
                    deleteCaseOfferButton.setVisible(true);
                    deleteCaseOfferButton.setDisable(false);
                    deleteCaseOfferButton.setText("Slet " + listOfChosenOffers.size() + " tilbud");
                } else{
                    deleteCaseOfferButton.setVisible(false);
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
                    deleteGrantingsButton.setVisible(true);
                    deleteGrantingsButton.setDisable(false);
                    deleteGrantingsButton.setText("Slet " + listOfChosenGrantings.size() + " ydelse");
                    if(listOfChosenGrantings.size() > 1) deleteGrantingsButton.setText("Slet " + listOfChosenGrantings.size() + " ydelser");
                } else{
                    deleteGrantingsButton.setVisible(false);
                    deleteGrantingsButton.setDisable(true);
                    deleteGrantingsButton.setText("Ingen ydelser valgt");
                }
            }
        });
    }

    //endregion

    //region meeting


    @Override
    public void onCreateMeeting(IEventListener<?> listener) {
        createMeetingSubscribers.add(listener);
    }

    @FXML
    void createMeeting(ActionEvent event) {
        createMeetingSubscribers.forEach(listener -> listener.onAction(null));
    }

    //endregion

    //region theme stuff
    private ObservableSet<IThemeUI> listOfChosenThemes = FXCollections.observableSet();
    private ObservableSet<IThemeUI> addedThemeUIs =  FXCollections.observableSet();

    @FXML
    private VBox themesContainer;

    @FXML
    private VBox caseThemeWrapper;

    @FXML
    private JFXButton addCaseThemebutton;

    @FXML
    private JFXButton deleteCaseThemeButton;

    @FXML
    private JFXButton saveCaseThemeButton;

    @FXML
    void addCaseTheme(ActionEvent event) {
        IThemeUI theme = new ThemeController();
        theme.onThemeSelected(data -> {
            if(data.isSelected()) listOfChosenThemes.add(data);
            else listOfChosenThemes.remove(data);
        });
        caseThemeWrapper.getChildren().add(theme.getView());
        addedThemeUIs.add(theme);
    }

    @FXML
    void deleteCaseThemes(ActionEvent event) {
        for (IThemeUI theme : listOfChosenThemes) {
            caseThemeWrapper.getChildren().remove(theme.getView());
            addedThemeUIs.remove(theme);
        }
        listOfChosenThemes.clear();
    }

    @FXML
    void saveCaseThemes(ActionEvent event) {
        Set<ThemeData> themeDatas = new HashSet<>();
        for (IThemeUI theme : addedThemeUIs) {
            ThemeData themeData = new ThemeData(theme.getTheme(), theme.getSubtheme(), theme.getLevelOfFunction());
            
            if (themeData.getThemeEnum() == null || theme.getSubtheme().length() == 0 || theme.getLevelOfFunction() == null) {
                // TODO: 23/05/18 lav popup box her måske?
                //System.out.println("Fejl i tema data");
            } else {
                themeDatas.add(themeData);
            }
        }
        //System.out.println(themeDatas);
    }

    private void setupUpThemesSection() {
        listOfChosenThemes.addListener(new SetChangeListener<IThemeUI>() {
            @Override
            public void onChanged(Change<? extends IThemeUI> change) {
                if(listOfChosenThemes.size() > 0){
                    deleteCaseThemeButton.setVisible(true);
                    deleteCaseThemeButton.setDisable(false);
                    deleteCaseThemeButton.setText("Slet " + listOfChosenThemes.size() + " tema" + ((listOfChosenThemes.size() > 1) ? "er" : ""));
                } else{
                    deleteCaseThemeButton.setVisible(false);
                    deleteCaseThemeButton.setDisable(true);
                    deleteCaseThemeButton.setText("Ingen temaer valgt");
                }
            }
        });

        addedThemeUIs.addListener(new SetChangeListener<IThemeUI>() {
            @Override
            public void onChanged(Change<? extends IThemeUI> change) {
                if (addedThemeUIs.size() > 0) {
                    saveCaseThemeButton.setVisible(true);
                    saveCaseThemeButton.setDisable(false);
                    saveCaseThemeButton.setText("Gem " + addedThemeUIs.size() + " tema" + ((addedThemeUIs.size() > 1) ? "er" : ""));
                }else{
                    saveCaseThemeButton.setVisible(false);
                    saveCaseThemeButton.setDisable(true);
                    saveCaseThemeButton.setText("Ingen temaer tilføjet");
                }
            }
        });
    }
    //endregion


    @FXML
    private JFXButton editCaseSpecialCircumstancesButton;

    @FXML
    private TextField caseSpecialCircumstancesField;

    @FXML
    private JFXButton editCaseCitizenMunicipalityButton;

    @FXML
    private TextField caseCitizenMunicipalityField;

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
    private TextField caseCitizenAgreementField;

    @FXML
    private JFXButton editCaseCitizenAgreementButton;

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
        setupUpThemesSection();

    }

    @Override
    public void onCaseCitizenMunicipality (IEventListener<String> listener) { saveCitizenMunicipalitySubscribers.add(listener);}
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
    public void onCaseCitzenAgreement(IEventListener<String> listener) {saveCitizenAgreementSubscribers.add(listener);}
    @Override
    public void onCaseSpecialCircumstancesField(IEventListener<String> listener) {saveSpecialCircumstancesSubscribers.add(listener); }
    @Override
    public void onCaseCitizenInformation (IEventListener<String> listener) {editCitizenInformationSubscribers.add(listener); }

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
    void editCitizenAgreement (ActionEvent event) {
        if(editCaseCitizenAgreementButton.getText().equals("Rediger")){
            caseCitizenAgreementField.setEditable(true);
            caseCitizenAgreementField.requestFocus();
            caseCitizenAgreementField.getStyleClass().add("editing");
            editCaseCitizenAgreementButton.setText("Gem");
            editCaseCitizenAgreementButton.getStyleClass().add("editing");
        } else if(editCaseCitizenAgreementButton.getText().equals("Gem")){
            caseCitizenAgreementField.setEditable(false);
            editCaseCitizenAgreementButton.setText("Rediger");
            caseCitizenAgreementField.getStyleClass().remove("editing");
            editCaseCitizenAgreementButton.getStyleClass().remove("editing");
            saveCitizenAgreementSubscribers.forEach(listener -> listener.onAction(caseCitizenAgreementField.getText()));
        }

    }


    @FXML
    void setEditCaseCitizenMunicipalityButton (ActionEvent event) {
        if(editCaseCitizenMunicipalityButton.getText().equals("Rediger")){
            caseCitizenMunicipalityField.setEditable(true);
            caseCitizenMunicipalityField.requestFocus();
            caseCitizenMunicipalityField.getStyleClass().add("editing");
            editCaseCitizenMunicipalityButton.setText("Gem");
            editCaseCitizenMunicipalityButton.getStyleClass().add("editing");
        } else if(editCaseCitizenMunicipalityButton.getText().equals("Gem")){
            caseCitizenMunicipalityField.setEditable(false);
            editCaseCitizenMunicipalityButton.setText("Rediger");
            caseCitizenMunicipalityField.getStyleClass().remove("editing");
            editCaseCitizenMunicipalityButton.getStyleClass().remove("editing");
            saveCitizenMunicipalitySubscribers.forEach(listener -> listener.onAction(caseCitizenMunicipalityField.getText()));
        }

    }


    @FXML
    void setEditCaseSpecialCircumstancesButton (ActionEvent event) {
        if(editCaseSpecialCircumstancesButton.getText().equals("Rediger")){
            caseSpecialCircumstancesField.setEditable(true);
            caseSpecialCircumstancesField.requestFocus();
            caseSpecialCircumstancesField.getStyleClass().add("editing");
            editCaseSpecialCircumstancesButton.setText("Gem");
            editCaseSpecialCircumstancesButton.getStyleClass().add("editing");
        } else if(editCaseSpecialCircumstancesButton.getText().equals("Gem")){
            caseSpecialCircumstancesField.setEditable(false);
            editCaseSpecialCircumstancesButton.setText("Rediger");
            caseSpecialCircumstancesField.getStyleClass().remove("editing");
            editCaseSpecialCircumstancesButton.getStyleClass().remove("editing");
            saveSpecialCircumstancesSubscribers.forEach(listener -> listener.onAction(caseSpecialCircumstancesField.getText()));
        }

    }
    @FXML
    void setEditCaseCitizenInformation (ActionEvent event) {
        if(editCaseCitizenInformation.getText().equals("Rediger")){
            nameField.setEditable(true);
            CPRField.setEditable(true);
            cityField.setEditable(true);
            addressField.setEditable(true);
            civilStatusField.setEditable(true);
            phoneNumberField.setEditable(true);
            cellphoneNumberField.setEditable(true);
            nameField.getStyleClass().add("editing");
            CPRField.getStyleClass().add("editing");
            cityField.getStyleClass().add("editing");
            addressField.getStyleClass().add("editing");
            civilStatusField.getStyleClass().add("editing");
            phoneNumberField.getStyleClass().add("editing");
            cellphoneNumberField.getStyleClass().add("editing");
            editCaseCitizenInformation.setText("Gem");
            editCaseCitizenInformation.getStyleClass().add("editing");
        } else if(editCaseCitizenInformation.getText().equals("Gem")){
            nameField.setEditable(false);
            CPRField.setEditable(false);
            cityField.setEditable(false);
            addressField.setEditable(false);
            civilStatusField.setEditable(false);
            phoneNumberField.setEditable(false);
            cellphoneNumberField.setEditable(false);
            editCaseCitizenInformation.setText("Rediger");
            nameField.getStyleClass().remove("editing");
            CPRField.getStyleClass().remove("editing");
            cityField.getStyleClass().remove("editing");
            addressField.getStyleClass().remove("editing");
            civilStatusField.getStyleClass().remove("editing");
            phoneNumberField.getStyleClass().remove("editing");
            cellphoneNumberField.getStyleClass().remove("editing");
            editCitizenInformationSubscribers.forEach(listener -> listener.onAction(nameField.getText()));
            editCitizenInformationSubscribers.forEach(listener -> listener.onAction(CPRField.getText()));
            editCitizenInformationSubscribers.forEach(listener -> listener.onAction(cityField.getText()));
            editCitizenInformationSubscribers.forEach(listener -> listener.onAction(addressField.getText()));
            editCitizenInformationSubscribers.forEach(listener -> listener.onAction(civilStatusField.getText()));
            editCitizenInformationSubscribers.forEach(listener -> listener.onAction(phoneNumberField.getText()));
            editCitizenInformationSubscribers.forEach(listener -> listener.onAction(cellphoneNumberField.getText()));
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
    void citizenAgreementField(ActionEvent event) {

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

