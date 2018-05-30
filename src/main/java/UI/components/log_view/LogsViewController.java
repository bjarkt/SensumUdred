package UI.components.log_view;

import ACQ.*;
import UI.Secured;
import UI.components.Component;
import UI.components.log_view.ILogsView;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import java.util.Set;

public class LogsViewController extends Component implements ILogsView {

    @FXML
    private AnchorPane container;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox vbox;

    @FXML
    private JFXListView<IEventLog> tasksList;

    public LogsViewController() {
        super("logs_view.fxml", "Hændelseslog");
    }

    ObservableList<IEventLog> meetings = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tasksList.setExpanded(true);
        tasksList.setItems(meetings);
        tasksList.setCellFactory(param -> new Cell());
    }

    @Override
    public void tickList(Set<IEventLog> meetings) {
        this.meetings.clear();
        this.meetings.addAll(meetings);
    }

    @Override
    public void disableList() {
        tasksList.setDisable(true);
    }

    @Override
    public void enableList() {
        tasksList.setDisable(false);
    }

    static class Cell extends JFXListCell<IEventLog>{
        HBox hBox = new HBox();
        VBox vBox = new VBox();
        Label citizenName = new Label("");
        Label createdDate = new Label("11/03/1997");
        Label lastEditedDate = new Label("14/05/2018");
        Region spacer = new Region();
        public Cell(){
            super();
            vBox.getChildren().addAll(citizenName, createdDate);
            vBox.setAlignment(Pos.CENTER_LEFT);
            hBox.getChildren().addAll(vBox, spacer, lastEditedDate);
            citizenName.getStyleClass().add("eludicationsList_citizenName");
            createdDate.getStyleClass().add("eludicationsList_createdDate");
            lastEditedDate.getStyleClass().add("eludicationsList_lastEditedDate");
            hBox.setHgrow(spacer, Priority.ALWAYS);
            hBox.setAlignment(Pos.CENTER_LEFT);
            this.cellRippler.setRipplerFill(Color.rgb(42,112,226,0.7));
        }

        public void updateItem(IEventLog log, boolean empty){
            super.updateItem(log, empty);
            setText(null);
            setGraphic(null);
            if(log != null && !empty){
                citizenName.setText(log.getDescription());
                createdDate.setText(log.getMethodName());
                lastEditedDate.setText("Action: " + log.getLogAction() + ". Level: " + log.getLogLevel());
                setGraphic(hBox);
            }
        }

    }


}
