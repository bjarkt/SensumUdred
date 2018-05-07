package UI.components.popUp;

import UI.components.Component;
import UI.components.IEventListener;
import UI.components.popUp.IPopUpRequire;
import UI.components.popUp.IPopup;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXDrawer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PopUpController extends Component implements IPopup {

    private IPopUpRequire required;

    @FXML
    private StackPane container;

    @FXML
    private JFXDialog dialog;

    private JFXDialogLayout content;

    private Text subject;

    private Text message;

    public PopUpController(IPopUpRequire required) {
        super("popUp.fxml");
        setRequired(required);
        subject = new Text();
        message = new Text();
        subject.getStyleClass().add("dialog_subject");
        message.getStyleClass().add("dialog_message");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        content = new JFXDialogLayout();
        JFXButton button = new JFXButton("Fortsæt");
        button.getStyleClass().add("dialog_button");
        button.setOnAction(event -> close());
        content.setActions(button);
        content.setHeading(subject);
        content.setBody(message);
        dialog.setDialogContainer(content);
        dialog = new JFXDialog(container, content, JFXDialog.DialogTransition.CENTER);
        dialog.setOnDialogClosed(event -> {
            required.getParent().getChildren().remove(this.getView());
        });

    }

    @Override
    public void show(String subject, String message) {
        this.subject.setText(subject);
        this.message.setText(message);
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
    public void setRequired(IPopUpRequire required) {
        this.required = required;
    }
}
