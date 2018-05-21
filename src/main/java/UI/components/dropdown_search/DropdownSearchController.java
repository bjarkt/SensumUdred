package UI.components.dropdown_search;

import UI.components.Component;
import UI.components.all_elucidations_view.HomeViewController;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class DropdownSearchController extends Component implements IDropdownSearch {

    ObservableList<String> listView = FXCollections.observableArrayList("Lasse", "Dennis", "Bjarke", "Lavanbro", "Adrian", "Christian", "Christian", "Christian", "Christian", "Christian", "Christian", "Christian", "Christian", "Christian", "Christian", "Christian", "Christian", "Christian", "Christian", "Christian", "Christian", "Christian", "Christian", "Christian", "Christian", "Christian", "Christian", "Christian", "Christian", "Christian", "Christian", "Christian", "Christian", "Christian", "Christian", "Christian", "Christian", "Christian", "Christian");

    private IDropdownSearchRequire required;

    @FXML
    private TextField inputField;

    @FXML
    private JFXListView<String> results;

    @FXML
    private JFXButton addButton;

    public DropdownSearchController() {
        super("dropdown_search.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        results.setItems(listView);
        results.setCellFactory(param -> new DropdownSearchController.Cell());

    }

    @FXML
    void search(KeyEvent event) {
        System.out.println("Bum");
    }

    @Override
    public void setRequired(IDropdownSearchRequire required) {

    }



    static class Cell extends JFXListCell<String> {
        HBox hBox = new HBox();
        VBox vBox = new VBox();
        JFXCheckBox checkBox = new JFXCheckBox();
        Label citizenName = new Label("");
        Region spacer = new Region();
        boolean checked = false;

        public Cell() {
            super();
            vBox.getChildren().addAll(citizenName);
            vBox.setAlignment(Pos.CENTER_LEFT);
            hBox.getChildren().addAll(vBox, spacer, checkBox);
            citizenName.getStyleClass().add("eludicationsList_citizenName");
            hBox.setHgrow(spacer, Priority.ALWAYS);
            hBox.setAlignment(Pos.CENTER_LEFT);
            this.cellRippler.setRipplerFill(Color.rgb(42, 112, 226, 0.7));
            this.setOnMouseClicked(event -> {
                if(checked == true) {
                    checkBox.setSelected(false);
                    checked = false;
                } else if(checked == false) {
                    checkBox.setSelected(true);
                    checked = true;
                }
            });

        }

        public void updateItem(String name, boolean empty) {
            super.updateItem(name, empty);
            setText(null);
            setGraphic(null);
            if (name != null && !empty) {
                citizenName.setText(name);
                setGraphic(hBox);
            }
        }
    }

}
