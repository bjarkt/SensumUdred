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
import java.util.List;
import java.util.ResourceBundle;

public class DropdownSearchController<T> extends Component implements IDropdownSearch<T> {


    private IDropdownSearchRequire<T> required;

    // This list holds the reference to the search results.
    private ObservableList<T> searchResults;

    @FXML
    private TextField inputField;

    @FXML
    private JFXListView<T> results;

    @FXML
    private JFXButton addButton;

    public DropdownSearchController(IDropdownSearchRequire required) {
        super("dropdown_search.fxml");
        setRequired(required);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        results.setCellFactory(param -> required.getCellFactory());
    }

    @FXML
    void search(KeyEvent event) {
        System.out.println("Bum");
    }

    @Override
    public void updateList(List<T> searchResults) {
        this.searchResults = FXCollections.observableArrayList(searchResults);
        results.setItems(this.searchResults);
    }

    @Override
    public void setRequired(IDropdownSearchRequire required) {
        this.required = required;
    }





}
