package UI.components.data_prompt;

import ACQ.IElucidation;
import UI.Secured;
import UI.components.Component;
import ACQ.IEventListener;
import UI.components.textfield.ITextField;
import UI.components.textfield.TextFieldController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class DataPromptController extends Component implements IDataPrompt {

    List<IEventListener<List<String>>> onContinueSubscribers = new ArrayList<>();


    String promptText;

    private ObservableList<ITextField> textFields = FXCollections.observableArrayList();

    @FXML
    private Label dataprompt_question;

    @FXML
    private VBox fieldsWrapper;

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
        this.promptText = prompt;
        if(dataprompt_question != null) dataprompt_question.setText(this.promptText);
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
