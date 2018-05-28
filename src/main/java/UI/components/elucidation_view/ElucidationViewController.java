package UI.components.elucidation_view;

import ACQ.*;
import UI.Secured;
import UI.components.Component;
import UI.components.dropdown_search.DropdownSearchController;
import UI.components.dropdown_search.IDropdownSearch;
import UI.components.dropdown_search.IDropdownSearchRequire;
import UI.components.dropdown_search.NameCheckboxCell;
import UI.components.elucidation_view.granting.GrantingController;
import UI.components.elucidation_view.granting.IGrantingUI;
import UI.components.elucidation_view.textfield_with_checkbox.ITextFieldWithCheckbox;
import UI.components.elucidation_view.textfield_with_checkbox.TextFieldWithCheckboxController;
import UI.components.elucidation_view.theme.IThemeUI;
import UI.components.elucidation_view.theme.ThemeController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.*;

public class ElucidationViewController extends Component implements IElucidationView {

    /* Required interface: Reference to the current displayed elucidation. */
    private IElucidationViewRequire required;

    /* Lists of subscribers */
    private List<IEventListener<?>> leaveEludicationSubscribers = new ArrayList<>();
    private List<IEventListener<String>> saveCaseDescriptionSubscribers = new ArrayList<>();
    private List<IEventListener<String>> addNewOfferSubscribers = new ArrayList<>();
    private List<IEventListener<String[]>> deleteOfferSubscribers = new ArrayList<>();
    private List<IEventListener<String>> saveCitizenAgreementSubscribers = new ArrayList<>();
    private List<IEventListener<String>> saveCitizenMunicipalitySubscribers = new ArrayList<>();
    private List<IEventListener<String>> saveSpecialCircumstancesSubscribers = new ArrayList<>();
    private List<IEventListener<String[]>> editCitizenInformationSubscribers = new ArrayList<>();
    private List<IEventListener<?>> createMeetingSubscribers = new ArrayList<>();
    private List<IEventListener<Set<IThemeUI>>> addNewThemeSubscribers = new ArrayList<>();
    private List<IEventListener<Set<IThemeUI>>> deleteThemeSubscribers = new ArrayList<>();
    private List<IEventListener<?>> onSendMessageSubscriber = new ArrayList<>();
    private List<IEventListener<ElucidationState>> onToggleStateSubscribers = new ArrayList<>();
    private List<IEventListener<?>> onCloseCaseSubscribers = new ArrayList<>();


    /* Boolean attribute used for component scaling */
    private boolean isMobile;

    @Secured("addCaseworkerToCase")
    private IDropdownSearch<IProfile> caseWorkerSearcher;

    @FXML
    private VBox caseWorkerContainer;

    //region general elements
    @FXML
    private Label taskTitle;

    @Secured("")
    @FXML
    private JFXButton stateButton;

    @FXML
    private VBox headerVbox;

    @FXML
    private HBox headerHbox;

    @FXML
    private Region headerSpacer;

    @FXML
    private HBox headerLeft;

    @FXML
    private FlowPane headerRight;

    @FXML
    void toggleState(ActionEvent event) {
        if(required.getElucidation().getTask().getState() == ElucidationState.INQUIRY){
            onToggleStateSubscribers.forEach(listener -> listener.onAction(ElucidationState.CASE));
        } else {
            onToggleStateSubscribers.forEach(listener -> listener.onAction(ElucidationState.INQUIRY));
        }
    }

    @Override
    public void onToggleState(IEventListener<ElucidationState> listener) {
        onToggleStateSubscribers.add(listener);
    }


    @FXML
    private JFXButton closeToggleButton;

    @FXML
    void toggleCloseElucidation(ActionEvent event) {
        onCloseCaseSubscribers.forEach(listener -> listener.onAction(null));
    }

    @Override
    public void onCloseCase(IEventListener<?> listener) {
        onCloseCaseSubscribers.add(listener);
    }

    //endregion

    //region citizen_section

    @Secured("getAdminService")
    @FXML
    private JFXButton editCaseCitizenInformation;

    @FXML
    private TextField nameField;

    @FXML
    private TextField CPRField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField dateField;

    private String citizenPrevSSN;

    @Override
    public void onCaseCitizenInformation (IEventListener<String[]> listener) {editCitizenInformationSubscribers.add(listener); }


    @FXML
    void editCitizenInfo(ActionEvent event) {
        if(editCaseCitizenInformation.getText().equals("Rediger")){
            citizenPrevSSN = CPRField.getText();
            nameField.setEditable(true);
            CPRField.setEditable(true);
            phoneNumberField.setEditable(true);
            emailField.setEditable(true);
            nameField.getStyleClass().add("editing");
            CPRField.getStyleClass().add("editing");
            phoneNumberField.getStyleClass().add("editing");
            emailField.getStyleClass().add("editing");
            editCaseCitizenInformation.setText("Gem");
            editCaseCitizenInformation.getStyleClass().add("editing");
        } else if(editCaseCitizenInformation.getText().equals("Gem")){
            nameField.setEditable(false);
            CPRField.setEditable(false);
            phoneNumberField.setEditable(false);
            emailField.setEditable(false);
            nameField.getStyleClass().remove("editing");
            CPRField.getStyleClass().remove("editing");
            phoneNumberField.getStyleClass().remove("editing");
            emailField.getStyleClass().add("editing");
            editCaseCitizenInformation.getStyleClass().remove("editing");
            editCaseCitizenInformation.setText("Rediger");

            String[] data = new String[5];
            data[0] = citizenPrevSSN;
            data[1] = nameField.getText();
            data[2] = CPRField.getText();
            data[3] = phoneNumberField.getText();
            data[4] = emailField.getText();

            editCitizenInformationSubscribers.forEach(listener -> listener.onAction(data));
        }
    }

    //endregion

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

    //region grantings section

    private ObservableSet<IGrantingUI> listOfChosenGrantings = FXCollections.observableSet();
    private ObservableSet<IGranting> listOfGrantings = FXCollections.observableSet();

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
        IGrantingUI granting = new GrantingController();
        granting.onGrantingSelected(data -> {
            if(data.isSelected()) listOfChosenGrantings.add(data);
            else listOfChosenGrantings.remove(data);
        });

        grantingWrapper.getChildren().add(granting.getView());
    }

    @FXML
    void deleteGranting(ActionEvent event) {
        for (IGrantingUI granting : listOfChosenGrantings) {
            grantingWrapper.getChildren().remove(granting.getView());
        }
        listOfChosenGrantings.clear();
    }

    private void setupUpGrantingsSection(){
        listOfChosenGrantings.addListener(new SetChangeListener<IGrantingUI>() {
            @Override
            public void onChanged(Change<? extends IGrantingUI> change) {
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

        // Updates the list of grantings every time a change to the list happens.
        listOfGrantings.addListener(new SetChangeListener<IGranting>() {
            @Override
            public void onChanged(Change<? extends IGranting> change) {
                grantingWrapper.getChildren().clear();
                for (IGranting granting : listOfGrantings) {
                    IGrantingUI grantingUI = new GrantingController();
                    grantingUI.setData(granting.getDescription(),Integer.toString(granting.getParagraph()));
                    grantingUI.onGrantingSelected(data -> {
                        if(data.isSelected()) listOfChosenGrantings.add(data);
                        else listOfChosenGrantings.remove(data);
                    });
                    grantingWrapper.getChildren().add(grantingUI.getView());
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

        for (IEventListener<Set<IThemeUI>> sub : deleteThemeSubscribers) {
            sub.onAction(listOfChosenThemes);
        }

        listOfChosenThemes.clear();
    }

    @FXML
    void saveCaseThemes(ActionEvent event) {
        Set<IThemeUI> verifiedThemes = new HashSet<>();

        for (IThemeUI theme : addedThemeUIs) {
            if (theme.verifyData()) {
                verifiedThemes.add(theme);
            }
        }

        for (IEventListener<Set<IThemeUI>> sub : addNewThemeSubscribers) {
            sub.onAction(verifiedThemes);
        }
    }

    private void setupUpThemesSection() {
        listOfChosenThemes.addListener(new SetChangeListener<IThemeUI>() {
            @Override
            public void onChanged(Change<? extends IThemeUI> change) {
                changeButtonTextBasedOnListSize(listOfChosenThemes.size(), deleteCaseThemeButton, new String[]{"Slet", "tema", "temaer"}, "Ingen temaer valgt");
            }
        });

        addedThemeUIs.addListener(new SetChangeListener<IThemeUI>() {
            @Override
            public void onChanged(Change<? extends IThemeUI> change) {
                changeButtonTextBasedOnListSize(addedThemeUIs.size(), saveCaseThemeButton, new String[]{"Gem", "tema", "temaer"}, "Ingen temaer tilføjet");
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

    public ElucidationViewController(IElucidationViewRequire elucidationViewRequire) {
        super("elucidation_view.fxml", "Elucidation_Name");
        setRequired(elucidationViewRequire);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Makes view responsive
        elucidation_view_container.widthProperty().addListener((observable, oldValue, newValue) -> {
            if(isMobile && newValue.doubleValue() > 800){
                isMobile = false;
                elucidationView_horizontalLayout.getChildren().addAll(elucidationView_horizontalLayout_left, elucidationView_horizontalLayout_right);
                headerHbox.getChildren().remove(headerSpacer);
                headerHbox.getChildren().addAll(headerLeft, headerSpacer, headerRight);
                headerHbox.setHgrow(headerSpacer, Priority.ALWAYS);
            } else if(!isMobile && newValue.doubleValue() < 800){
                isMobile = true;
                elucidationView_verticalLayout.getChildren().addAll(elucidationView_horizontalLayout_left, elucidationView_horizontalLayout_right);
                headerVbox.getChildren().addAll(headerLeft, headerRight);
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

        /* Update data to user. */
        tick();

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
    public void onAddNewTheme(IEventListener<Set<IThemeUI>> listener) {
        addNewThemeSubscribers.add(listener);
    }

    @Override
    public void onDeleteTheme(IEventListener<Set<IThemeUI>> listener) {
        deleteThemeSubscribers.add(listener);
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

    @FXML
    void sendPopUp(ActionEvent event)   {
        onSendMessageSubscriber.forEach(iEventListener -> iEventListener.onAction(null) );
    }

    @Override
    public void onSendMessage(IEventListener<?> listener) {
        onSendMessageSubscriber.add(listener);
    }

    @Override
    public void onLeaveElucidation(IEventListener<?> listener) {
        leaveEludicationSubscribers.add(listener);
    }


    /**
     * Change button text based on list size, with plural word.
     * @param listSize size of list.
     * @param button which button.
     * @param positiveText array of string. example: new String[]{"Gem", "tema", "temaer"}. [0] element is verb, [1] is singular of word, [2] is plural of word.
     * @param negativeText text to display in negative situation.
     */
    private void changeButtonTextBasedOnListSize(int listSize, JFXButton button, String[] positiveText, String negativeText) {
        if (listSize > 0) {
            button.setVisible(true);
            button.setDisable(false);
            button.setText(positiveText[0] + " " + listSize + " " + ((listSize > 1) ? positiveText[2] : positiveText[1] ));
        } else {
            button.setVisible(false);
            button.setDisable(true);
            button.setText(negativeText);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick() {
        /* Tick generel info */
        taskTitle.setText("Sagsnummer: " + Long.toString(required.getElucidation().getId()));
        nameField.setText(required.getElucidation().getCitizen().getName());
        CPRField.setText(required.getElucidation().getCitizen().getSocialSecurityNumber());
        phoneNumberField.setText(required.getElucidation().getCitizen().getPhoneNumber());
        emailField.setText(required.getElucidation().getCitizen().getEmail());
        dateField.setText(required.getElucidation().getCreationDate().toString());

        if(required.getElucidation().getTask().getState() == ElucidationState.INQUIRY){
            this.caseDescriptionField.setText(((IInquiry)(required.getElucidation().getTask())).getDescription());
        } else{
            listOfGrantings.addAll(((ICase)(required.getElucidation().getTask())).getGrantings());
            // ((ICase)(elucidation.getTask())).getGrantings().
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRequired(IElucidationViewRequire required) {
        this.required = required;
    }
}

