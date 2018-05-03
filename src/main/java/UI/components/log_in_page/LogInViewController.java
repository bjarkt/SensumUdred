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

    @FXML
    private VBox inputForm;

    public LogInViewController(){super("log_in_view.fxml");}

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        JFXTextField validationField = new JFXTextField();
        validationField.setPromptText("With Validation..");
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("Input Required");
        validationField.getValidators().add(validator);
        validationField.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) validationField.validate();
        });
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
