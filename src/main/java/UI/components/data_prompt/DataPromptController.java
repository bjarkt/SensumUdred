package UI.components.data_prompt;

import ACQ.IEventListener;
import UI.components.Component;
import UI.components.textfield.ITextField;
import UI.components.textfield.TextFieldController;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DataPromptController extends Component implements IDataPrompt {

    List<IEventListener<List<String>>> onContinueSubscribers = new ArrayList<>();


    String promptText;

    private ObservableList<ITextField> textFields = FXCollections.observableArrayList();

    @FXML
    private VBox wrapper;

    @FXML
    private Label dataprompt_question;

    @FXML
    private VBox fieldsWrapper;

    @FXML
    private VBox contentWrapper;

    @FXML
    private HBox buttonWrapper;

    @FXML
    private JFXButton dataprompt_continueButton;


    public DataPromptController() {
        super("data_prompt.fxml", "Indtast data");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataprompt_question.setText(this.promptText);
    }

    @Override
    public void onContinue(IEventListener<List<String>> listener) {
        onContinueSubscribers.add(listener);
    }

    @Override
    public void addTextFields(String ...field) {
        for (String fieldName : field) {
            ITextField textField = new TextFieldController(fieldName);
            textFields.add(textField);
            fieldsWrapper.getChildren().add(textField.getView());
        }
    }

    @Override
    public void setPrompt(String prompt) {
        if(prompt == null) wrapper.getChildren().remove(dataprompt_question);
        else{
            this.promptText = prompt;
            dataprompt_question.setText(this.promptText);
        }
    }

    @Override
    public void setButtonText(String text) {
        if(text == null) contentWrapper.getChildren().remove(buttonWrapper);
        else {
            this.dataprompt_continueButton.setText(text);
        }
    }

    @FXML
    void next(ActionEvent event) {
        onContinueSubscribers.forEach(listener -> {
            List<String> answers = new ArrayList<>();
            for (ITextField textField : textFields) {
                answers.add(textField.getText());
            }
            listener.onAction(answers);
        });

        for (ITextField textField : textFields) {
            fieldsWrapper.getChildren().remove(textField.getView());
        }
        textFields.clear();

    }

}
