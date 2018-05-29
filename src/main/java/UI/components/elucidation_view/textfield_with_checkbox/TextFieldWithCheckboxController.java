package UI.components.elucidation_view.textfield_with_checkbox;

import ACQ.IEventListener;
import UI.components.Component;
import com.jfoenix.controls.JFXCheckBox;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TextFieldWithCheckboxController extends Component implements ITextFieldWithCheckbox {

    private List<IEventListener<ITextFieldWithCheckbox>> onTextFieldSelectedSubscribers = new ArrayList<>();

    private boolean selected;

    @FXML
    private AnchorPane textField_container;

    @FXML
    private TextField textField;

    @FXML
    private JFXCheckBox checkBox;


    public TextFieldWithCheckboxController() {
        super("textfield_with_checkbox.fxml");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textField.getStyleClass().add("editing");
        textField.requestFocus();
    }

    @Override
    public void onTextFieldSelected(IEventListener<ITextFieldWithCheckbox> listener) {
        onTextFieldSelectedSubscribers.add(listener);
    }

    @FXML
    void onTextEnter(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            textField.getStyleClass().remove("editing");
            textField.setEditable(false);
        }
    }


    @FXML
    void clickCheckBox(MouseEvent event) {
        if(selected) selected = false;
        else selected = true;
        onTextFieldSelectedSubscribers.forEach(listener -> listener.onAction(this));
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    public String getText(){
        return textField.getText();
    }



}

