package UI.components.popUp;

import UI.components.Component;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.net.URL;
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
    public void setRequired(IPopUpRequire required) {
        this.required = required;
    }
}
