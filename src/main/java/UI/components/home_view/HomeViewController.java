package UI.components.home_view;

import UI.components.Component;
import UI.components.search_bar.ISearchBar;
import UI.components.search_bar.SearchBarController;
import UI.components.search_bar_results.ISearchBarResults;
import UI.components.search_bar_results.SearchBarResultsController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXScrollPane;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeViewController extends Component implements IHomeView {

    private IHomeViewRequire required;

    @FXML
    private JFXMasonryPane myElucidationsContainer;

    public HomeViewController() {
        super("home_view.fxml", "Hjem");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {



    }

    @Override
    public void setRequired(IHomeViewRequire required) {
        this.required = required;
    }
}
