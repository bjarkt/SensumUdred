package UI.components.usermanagement_view;

import ACQ.*;
import UI.components.Component;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

public class UserManagementViewController extends Component implements IUserManagementView {

    List<IEventListener<String>> onLoadDataSubscribers = new ArrayList<>();
    List<IEventListener<Pair<String,String>>> onUpdateFirstNameSubscribers = new ArrayList<>();
    List<IEventListener<Pair<String,String>>> onUpdateLastNameSubscribers = new ArrayList<>();
    List<IEventListener<Pair<String,String>>> onUpdateEmailSubscribers = new ArrayList<>();
    List<IEventListener<Pair<String,String>>> onUpdatePhoneSubscribers = new ArrayList<>();
    List<IEventListener<Pair<String,String>>> onUpdateSSNSubscribers = new ArrayList<>();
    List<IEventListener<String[]>> onCreateUserSubscribers = new ArrayList<>();
    List<IEventListener<String[]>> onCreateAccountSubscribers = new ArrayList<>();
    List<IEventListener<Pair<String,String>>> onUpdateUserNameSubscribers = new ArrayList<>();
    List<IEventListener<Pair<String,String>>> onUpdatePasswordSubscribers = new ArrayList<>();
    List<IEventListener<Pair<String, Boolean>>> onActivateAccountSubscribers = new ArrayList<>();
    List<IEventListener<Pair<String, Boolean>>> onDeactivateAccountSubscribers = new ArrayList<>();
    List<IEventListener<Pair<String, Integer>>> onUpdateSecurityLevelSubscribers = new ArrayList<>();

    @FXML
    private AnchorPane container;

    @FXML
    private VBox vbox;

    @FXML
    private TextField cpr;

    @FXML
    private TextField ssnload;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField email;

    @FXML
    private TextField phone;

    @FXML
    private TextField username;

    @FXML
    private TextField pass;

    @FXML
    private JFXRadioButton citizen;

    @FXML
    private ToggleGroup securityLevel;

    @FXML
    private JFXRadioButton caseworker;

    @FXML
    private JFXRadioButton admin;

    public UserManagementViewController() {
        super("usermanagement_view.fxml", "Brugeradministration");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void loadData(ActionEvent event) {
        if(cpr.getText().length() > 0) onLoadDataSubscribers.forEach(listener -> listener.onAction(cpr.getText()));
        else onLoadDataSubscribers.forEach(listener -> listener.onAction(null));
    }

    @FXML
    void createOrUpdateUser(ActionEvent event) {
        String[] userDetails = new String[6];
        userDetails[0] = cpr.getText();
        userDetails[1] = ssnload.getText();
        userDetails[2] = firstName.getText();
        userDetails[3] = lastName.getText();
        userDetails[4] = phone.getText();
        userDetails[5] = email.getText();
        onCreateUserSubscribers.forEach(listener -> listener.onAction(userDetails));
    }

    @FXML
    void createAccount(ActionEvent event) {
        String[] details = new String[4];
        details[0] = ssnload.getText();
        details[1] = username.getText();
        details[2] = pass.getText();
        if(citizen.isSelected()) details[3] = String.valueOf(0);
        if(caseworker.isSelected()) details[3] = String.valueOf(500);
        if(admin.isSelected()) details[3]  = String.valueOf(1000);
        onCreateAccountSubscribers.forEach(listener -> listener.onAction(details));
    }

    @FXML
    void activateAccount(ActionEvent event) {
        Pair<String, Boolean> data = new Pair<>(username.getText(), Boolean.TRUE);
        onActivateAccountSubscribers.forEach(pairIEventListener -> pairIEventListener.onAction(data));
    }

    @FXML
    void updatePassword(ActionEvent event) {
        Pair<String, String> data = new Pair<>(username.getText(), pass.getText());
        onUpdatePasswordSubscribers.forEach(pairIEventListener -> pairIEventListener.onAction(data));
    }

    @FXML
    void updateSecurityLevel(ActionEvent event) {
        int securityLevel = 0;
        if(citizen.isSelected()) securityLevel = 0;
        if(caseworker.isSelected()) securityLevel = 500;
        if(admin.isSelected()) securityLevel = 1000;
        Pair<String, Integer> data = new Pair<>(username.getText(), securityLevel);
        onUpdateSecurityLevelSubscribers.forEach(pairIEventListener -> pairIEventListener.onAction(data));
    }

    @FXML
    void updateUsername(ActionEvent event) {
        Pair<String, String> data = new Pair<>(ssnload.getText(), username.getText());
        onUpdateUserNameSubscribers.forEach(pairIEventListener -> pairIEventListener.onAction(data));
    }

    @FXML
    void deactivateAccount(ActionEvent event) {
        Pair<String, Boolean> data = new Pair<>(username.getText(), Boolean.FALSE);
        onActivateAccountSubscribers.forEach(pairIEventListener -> pairIEventListener.onAction(data));
    }

    @Override
    public void onLoadData(IEventListener<String> listener) {
        onLoadDataSubscribers.add(listener);
    }

    @Override
    public void updateFirstName(IEventListener<Pair<String, String>> listener) {
        onUpdateFirstNameSubscribers.add(listener);
    }

    @Override
    public void updateLastName(IEventListener<Pair<String, String>> listener) {
        onUpdateLastNameSubscribers.add(listener);
    }

    @Override
    public void updateEmail(IEventListener<Pair<String, String>> listener) {
        onUpdateEmailSubscribers.add(listener);
    }

    @Override
    public void updatePhoneNumber(IEventListener<Pair<String, String>> listener) {
        onUpdatePhoneSubscribers.add(listener);
    }

    @Override
    public void updateSSN(IEventListener<Pair<String, String>> listener) {
        onUpdateSSNSubscribers.add(listener);
    }

    @Override
    public void createUser(IEventListener<String[]> listener) {
        onCreateUserSubscribers.add(listener);
    }

    @Override
    public void updateUserName(IEventListener<Pair<String,String>> listener) {
        onUpdateUserNameSubscribers.add(listener);
    }

    @Override
    public void updatePassword(IEventListener<Pair<String,String>> listener) {
        onUpdatePasswordSubscribers.add(listener);
    }

    @Override
    public void createAccount(IEventListener<String[]> listener) {
        onCreateAccountSubscribers.add(listener);
    }

    @Override
    public void activateAccount(IEventListener<Pair<String, Boolean>> listener) {
        onActivateAccountSubscribers.add(listener);
    }

    @Override
    public void deactivateAccount(IEventListener<Pair<String, Boolean>> listener) {
        onDeactivateAccountSubscribers.add(listener);
    }

    @Override
    public void updateSecurityLevel(IEventListener<Pair<String, Integer>> listener) {
        onUpdateSecurityLevelSubscribers.add(listener);
    }

    @Override
    public void setIUser(IUser user) {
        if(user != null) {
            firstName.setText(user.getFirstName());
            lastName.setText(user.getLastName());
            email.setText(user.getEmail());
            phone.setText(user.getPhoneNumber());
            ssnload.setText(user.getSocialSecurityNumber());
        } else{
            firstName.setText("");
            lastName.setText("");
            email.setText("");
            phone.setText("");
            ssnload.setText("");
        }
    }
}
