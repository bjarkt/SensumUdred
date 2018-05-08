package UI.components.search_bar;

import UI.components.Component;
import UI.components.IEventListener;
import UI.components.search_bar_results.SearchBarResultsController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SearchBarController extends Component implements ISearchBar {

    private List<IEventListener<String>> onTypeSubscribers = new ArrayList<>();

    private ISearchBarRequire required;

    @FXML
    private FlowPane search_container;

    @FXML
    private TextField input;

    private SearchBarResultsController searchBarResults;

    public SearchBarController() {
        super("search_bar.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        searchBarResults = new SearchBarResultsController();
    }

    @Override
    public void onType(IEventListener<String> listener) {
        onTypeSubscribers.add(listener);
    }

    @FXML
    void onInputChanged(KeyEvent event) {
        onTypeSubscribers.forEach(listener -> listener.onAction(input.getCharacters().toString()));
    }

    @Override
    public void setRequired(ISearchBarRequire required) {
        this.required = required;
    }
}
