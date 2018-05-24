package UI.components.dropdown_search;

import UI.components.Component;
import ACQ.IEventListener;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

import javax.script.Bindings;
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
    private HBox buttonContainer;

    private JFXButton addButton;

    public DropdownSearchController(IDropdownSearchRequire required) {
        super("dropdown_search.fxml");
        setRequired(required);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        results.setCellFactory(param -> {
            required.getCellFactory().addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                T item = required.getCellFactory().getItem();
                if(results.getSelectionModel().getSelectedItems().contains(item)){
                    results.getSelectionModel().getSelectedItems().remove(item);
                } else{
                    results.getSelectionModel().getSelectedItems().add(item);
                }
                event.consume();
            });
            return required.getCellFactory();
        });

        results.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        addButton = new JFXButton("Tilføj");
        addButton.getStyleClass().addAll("flat-button", "flat-button_outlined");
        addButton.setOnAction(clickEvent -> {
            done();
        });

        results.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<T>() {
            @Override
            public void onChanged(Change<? extends T> c) {
                for (T t : results.getSelectionModel().getSelectedItems()) {
                    System.out.println(t);
                }
            }
        });

    }

    @FXML
    void search(KeyEvent event) {
        if(inputField.getText().length() == 0){
            System.out.println(inputField.getText().length());
        } else{
            onTypeSubscribers.forEach(listener -> listener.onAction(inputField.getText()));
        }
    }

    void done() {
        Set<T> selectedItems = new HashSet<>();
        for (T t : results.getSelectionModel().getSelectedItems()) {
            selectedItems.add(t);
        }
        onDoneSubscribers.forEach(listener -> {
            listener.onAction(selectedItems);
        });
        inputField.clear();
        removeButton();
    }

    @Override
    public void updateList(Set<T> searchResults) {
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
        results.setMaxHeight(Region.USE_COMPUTED_SIZE);
        results.setVisible(true);
    }

    @Override
    public void collapse() {
        results.setMaxHeight(0);
        results.setVisible(false);
    }

    private void displayButton(){
        if(!buttonContainer.getChildren().contains(addButton)) buttonContainer.getChildren().add(addButton);
    }

    private void removeButton(){
        buttonContainer.getChildren().remove(addButton);
    }

    @Override
    public void setRequired(IDropdownSearchRequire required) {
        this.required = required;
    }

}
