package UI.components.send_popUp;

import UI.components.Component;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;


import javax.swing.*;
import javax.xml.soap.Text;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SendPopUpController extends Component implements ISendPopup {


    private ISendPopUpRequire required;

    @FXML
    private StackPane container;

    @FXML
    private JFXDialog dialog;

    private JFXDialogLayout content;

    public SendPopUpController(ISendPopUpRequire required) {
        super("SendPopUp.fxml");
        setRequired(required);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources){

    }

    @Override
    public void show(String subject, String message) {
        required.getParent().getChildren().add(this.getView());
        required.getParent().setTopAnchor(this.getView(), .0);
        required.getParent().setLeftAnchor(this.getView(), .0);
        required.getParent().setRightAnchor(this.getView(), .0);
        required.getParent().setBottomAnchor(this.getView(), .0);
        dialog.show();
    }

    @Override
    public void close() {
        dialog.close();
    }

    @Override
    public void setRequired(ISendPopUpRequire required) {
        this.required = required;
    }
}
