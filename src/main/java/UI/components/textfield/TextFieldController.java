package UI.components.textfield;

import UI.components.Component;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class TextFieldController extends Component implements ITextField {

    @FXML
    private AnchorPane textField_container;

    @FXML
    private TextField textField;

    private String promptText;

    public TextFieldController(String promptText) {
        super("textfield.fxml");
        this.promptText = promptText;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setPromptText(this.promptText);
    }

    @Override
    public String getText(){
        return textField.getText();
    }

    @Override
    public void setPromptText(String prompt) {
        textField.setPromptText(prompt);
    }
}

