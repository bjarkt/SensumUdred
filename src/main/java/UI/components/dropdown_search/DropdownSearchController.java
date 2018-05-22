package UI.components.dropdown_search;

import ACQ.IUser;
import UI.components.Component;
import UI.components.IEventListener;
import UI.components.all_elucidations_view.HomeViewController;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import java.util.*;

public class DropdownSearchController<T> extends Component implements IDropdownSearch<T> {

    private List<IEventListener<String>> onTypeSubscribers = new ArrayList<>();
    private List<IEventListener<Set<T>>> onDoneSubscribers = new ArrayList<>();

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
        collapse();
        addButton.setVisible(false);
    }

    @FXML
    void search(KeyEvent event) {
        if(inputField.getText().length() == 0){
            addButton.setVisible(false);
            collapse();
        } else{
            addButton.setVisible(true);
            expand();
            onTypeSubscribers.forEach(listener -> listener.onAction(inputField.getText()));
        }
    }

    @FXML
    void done(ActionEvent event) {Set<T> selectedItems = new HashSet<>();
        for (T t : results.getSelectionModel().getSelectedItems()) {
            selectedItems.add(t);
        }
        onDoneSubscribers.forEach(listener -> {
            listener.onAction(selectedItems);
        });
        collapse();
        addButton.setVisible(false);
    }

    @Override
    public void updateList(List<T> searchResults) {
        this.searchResults = FXCollections.observableArrayList(searchResults);
        results.setItems(this.searchResults);
    }

    @Override
    public void onType(IEventListener<String> listener) {
        onTypeSubscribers.add(listener);
    }

    @Override
    public void onDone(IEventListener<Set<T>> listIEventListener) {
        onDoneSubscribers.add(listIEventListener);
    }

    @Override
    public void expand() {
        results.setMaxHeight(300);
        results.setVisible(true);
    }

    @Override
    public void collapse() {
        results.setMaxHeight(0);
        results.setVisible(false);
    }

    @Override
    public void setRequired(IDropdownSearchRequire required) {
        this.required = required;
    }





}
