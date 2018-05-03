package UI.components.log_in_page;

import UI.components.Component;
import UI.components.IEventListener;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LogInViewController extends Component implements ILogInView{

    private List<IEventListener<?>> onLogInSubscribers = new ArrayList<>();

    public LogInViewController(){super("log_in_view.fxml");}

    @FXML
    private VBox inputForm;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        JFXTextField username = new JFXTextField();
        username.setPromptText("Brugernavn");

        RequiredFieldValidator usernameValidator = new RequiredFieldValidator();
        usernameValidator.setMessage("Brugernavn påkrævet");
        username.getValidators().add(usernameValidator);
        username.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) username.validate();
        });

        JFXTextField password = new JFXTextField();
        password.setPromptText("Password");

        RequiredFieldValidator passwordValidator = new RequiredFieldValidator();
        passwordValidator.setMessage("Password påkrævet");
        password.getValidators().add(passwordValidator);
        password.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) password.validate();
        });

        inputForm.getChildren().addAll(username, password);

    }

    @Override
    public void onLogIn(IEventListener<?> listener) {
        onLogInSubscribers.add(listener);
    }

    @FXML
    void logIn(ActionEvent event) {
        onLogInSubscribers.forEach(listener -> listener.onAction(null));
    }


}
