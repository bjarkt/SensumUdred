package UI.components.elucidation_view.granting;

import UI.components.Component;
import ACQ.IEventListener;
import com.jfoenix.controls.JFXCheckBox;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.*;
import java.util.List;

public class GrantingController extends Component implements IGranting {

    private List<IEventListener<IGranting>> onGrantingSelectedSubscribers = new ArrayList<>();

    private boolean selected;

    @FXML
    private AnchorPane granting_container;

    @FXML
    private TextField textField;

    @FXML
    private TextField paragraph;

    @FXML
    private JFXCheckBox checkBox;


    public GrantingController() {
        super("granting.fxml");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textField.getStyleClass().add("editing");
        textField.requestFocus();
    }

    @Override
    public void onGrantingSelected(IEventListener<IGranting> listener) {
        onGrantingSelectedSubscribers.add(listener);
    }


    @FXML
    void onParagraphEnter(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            textField.setEditable(false);
            paragraph.setEditable(false);
            paragraph.getStyleClass().remove("editing");
        }
    }

    @FXML
    void onTextEnter(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            paragraph.requestFocus();
            textField.getStyleClass().remove("editing");
            paragraph.getStyleClass().add("editing");
        }
    }


    @FXML
    void clickCheckBox(MouseEvent event) {
        if(selected) selected = false;
        else selected = true;
        onGrantingSelectedSubscribers.forEach(listener -> listener.onAction(this));
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    public String getText(){
        return textField.getText();
    }

    public String getParagraph(){
        return paragraph.getText();
    }



}

