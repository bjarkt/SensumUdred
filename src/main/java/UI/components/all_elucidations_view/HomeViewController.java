package UI.components.all_elucidations_view;

import ACQ.IElucidation;
import ACQ.IProfile;
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

public class HomeViewController extends Component implements IHomeView {

    private List<JFXButton> buttons;

    private List<IEventListener<?>> elucidationListSubscribers = new ArrayList<>();

    private IHomeViewRequire required;

    @FXML
    private AnchorPane container;

    @Secured("createInquiry")
    @FXML
    private JFXButton newInquiryButton;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox vbox;

    @FXML
    private JFXListView<String> tasksList;

    public HomeViewController() {
        super("home_view.fxml", "Hjem");
        buttons = new ArrayList<>();
    }

    ObservableSet<IElucidation> elucidations = FXCollections.observableSet();

    ObservableList<String> listView = FXCollections.observableArrayList("Lasse", "Dennis", "Bjarke", "Lavanbro", "Adrian", "Christian");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttons.add(newInquiryButton);
        this.newInquiryButton = newInquiryButton;
        tasksList.setExpanded(true);
        tasksList.setItems(listView);
        tasksList.setCellFactory(param -> new Cell());
    }

    @FXML
    void taskListClicked(MouseEvent event) {
        System.out.println("Clicked" + tasksList.getSelectionModel().getSelectedItems());
        elucidationListSubscribers.forEach(listener -> listener.onAction(null));
    }

    @Override
    public void onElucidationClick(IEventListener<?> listener) {
        elucidationListSubscribers.add(listener);
    }

    static class Cell extends JFXListCell<String>{
        HBox hBox = new HBox();
        VBox vBox = new VBox();
        JFXCheckBox checkBox = new JFXCheckBox();
        Label citizenName = new Label("");
        Label createdDate = new Label("11/03/1997");
        Label lastEditedDate = new Label("14/05/2018");
        Region spacer = new Region();
        public Cell(){
            super();
            vBox.getChildren().addAll(citizenName, createdDate);
            vBox.setAlignment(Pos.CENTER_LEFT);
            hBox.getChildren().addAll(vBox, spacer, checkBox);
            citizenName.getStyleClass().add("eludicationsList_citizenName");
            createdDate.getStyleClass().add("eludicationsList_createdDate");
            lastEditedDate.getStyleClass().add("eludicationsList_lastEditedDate");
            hBox.setHgrow(spacer, Priority.ALWAYS);
            hBox.setAlignment(Pos.CENTER_LEFT);
            this.cellRippler.setRipplerFill(Color.rgb(42,112,226,0.7));
        }

        public void updateItem(String name, boolean empty){
            super.updateItem(name, empty);
            setText(null);
            setGraphic(null);
            if(name != null && !empty){
                citizenName.setText(name);
                setGraphic(hBox);
            }
        }

    }


}
