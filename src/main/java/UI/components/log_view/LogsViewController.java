package UI.components.log_view;

import ACQ.*;
import UI.components.Component;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.net.URL;
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
        Label logDescription = new Label("");
        Label methodName = new Label("11/03/1997");
        Label logActionAndLevel = new Label("14/05/2018");
        Region spacer = new Region();
        public Cell(){
            super();
            vBox.getChildren().addAll(logDescription, methodName);
            vBox.setAlignment(Pos.CENTER_LEFT);
            hBox.getChildren().addAll(vBox, spacer, logActionAndLevel);
            logDescription.getStyleClass().add("eludicationsList_citizenName");
            methodName.getStyleClass().add("eludicationsList_createdDate");
            logActionAndLevel.getStyleClass().add("eludicationsList_lastEditedDate");
            hBox.setHgrow(spacer, Priority.ALWAYS);
            hBox.setAlignment(Pos.CENTER_LEFT);
            this.cellRippler.setRipplerFill(Color.rgb(42,112,226,0.7));
        }

        public void updateItem(IEventLog log, boolean empty){
            super.updateItem(log, empty);
            setText(null);
            setGraphic(null);
            if(log != null && !empty){
                logDescription.setText(log.getDescription());
                methodName.setText(log.getMethodName());
                logActionAndLevel.setText("Action: " + log.getLogAction() + ". Level: " + log.getLogLevel());
                setGraphic(hBox);
            }
        }

    }


}
