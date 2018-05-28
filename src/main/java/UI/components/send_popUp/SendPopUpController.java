package UI.components.send_popUp;

import UI.components.Component;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import javax.xml.soap.Text;
import java.net.URL;
import java.util.ResourceBundle;

public class SendPopUpController extends Component implements ISendPopup {

    private ISendPopUpRequire required;

    @FXML
    private JFXDialog dialog;

    @FXML
    private StackPane container;

    private JFXDialogLayout content;

    private Label lable;

    private ComboBox<String> comboBox;

    // private CheckBox cb1 = new CheckBox("Godkend");

    private HBox sendMessage;

    ObservableList<String> list = FXCollections.observableList(Arrays.asList(
            "OUH", "SHF", "SHS", "SVS", "FKS", "VGS")
    );

    public SendPopUpController(ISendPopUpRequire required) {
        super("SendPopUp.fxml");
        setRequired(required);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        content = new JFXDialogLayout();

        sendMessage = new HBox();
        comboBox = new ComboBox<>();
        comboBox.setItems(list);
        sendMessage.getChildren().add(comboBox);
        content.setBody(sendMessage);

        container = new StackPane();
        dialog.setDialogContainer(container);


        dialog.setContent(content);

        JFXButton button = new JFXButton("Send");
        button.setOnAction(event -> close());
        content.setActions(button);

        content.setHeading(new Label("Heading"));
        dialog.setOnDialogClosed(event -> {
            required.getParent().getChildren().remove(this.getView());
        });

    }

    @Override
    public void show() {
        if(!required.getParent().getChildren().contains(this.getView())) {
            required.getParent().getChildren().add(this.getView());
            required.getParent().setTopAnchor(this.getView(), .0);
            required.getParent().setLeftAnchor(this.getView(), .0);
            required.getParent().setRightAnchor(this.getView(), .0);
            required.getParent().setBottomAnchor(this.getView(), .0);
        }
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
