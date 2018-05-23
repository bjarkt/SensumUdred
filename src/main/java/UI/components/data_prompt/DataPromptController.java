package UI.components.data_prompt;

import ACQ.IElucidation;
import UI.Secured;
import UI.components.Component;
import ACQ.IEventListener;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DataPromptController extends Component implements IDataPrompt {

    private IDataPromptRequire required;

    public DataPromptController() {
        super("data_prompt.fxml", "Hjem");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
