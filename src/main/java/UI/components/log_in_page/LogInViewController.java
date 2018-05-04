package UI.components.log_in_page;

import UI.components.Component;
import UI.components.IEventListener;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LogInViewController extends Component implements ILogInView{

    private List<IEventListener<String[]>> onLogInSubscribers = new ArrayList<>();

    public LogInViewController(){super("log_in_view.fxml");}

    private JFXTextField username;
    private JFXTextField password;

    @FXML
    private JFXButton loginButton;

    @FXML
    private Label errorLabel;

    @FXML
    private VBox inputForm;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        username = new JFXTextField();
        username.setPromptText("Brugernavn");
        username.setOnAction(this::logIn);

        RequiredFieldValidator usernameValidator = new RequiredFieldValidator();
        usernameValidator.setMessage("Brugernavn påkrævet");
        username.getValidators().add(usernameValidator);
        username.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) username.validate();
        });

        password = new JFXTextField();
        password.setPromptText("Password");
        password.setOnAction(this::logIn);

        RequiredFieldValidator passwordValidator = new RequiredFieldValidator();
        passwordValidator.setMessage("Password påkrævet");
        password.getValidators().add(passwordValidator);
        password.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) password.validate();
        });

        inputForm.getChildren().addAll(username, password);

    }

    @Override
    public void onLogIn(IEventListener<String[]> listener) {
        onLogInSubscribers.add(listener);
    }

    @FXML
    void logIn(ActionEvent event) {
        errorLabel.setVisible(false);
        errorLabel.setText("");
        String[] credentials = new String[2];
        credentials[0] = username.getText();
        credentials[1] = password.getText();
        onLogInSubscribers.forEach(listener -> listener.onAction(credentials));
    }

    @Override
    public void writeError(String message) {
        errorLabel.setVisible(true);
        errorLabel.setText(message);
    }
}
