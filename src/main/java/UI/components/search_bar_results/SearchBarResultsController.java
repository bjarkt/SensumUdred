package UI.components.search_bar_results;

import UI.components.Component;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchBarResultsController extends Component implements ISearchBarResults{

    @FXML
    private JFXListView<Label> results_list;

    public SearchBarResultsController() {
        super("search_bar_results.fxml", "Søg");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        for (int i = 0; i < 100; i++) {
            results_list.getItems().add(new Label("Item " + i));
        }

    }

}
