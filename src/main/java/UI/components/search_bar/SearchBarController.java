package UI.components.search_bar;

import UI.components.Component;
import UI.components.IEventListener;
import UI.components.home_view.IHomeView;
import UI.components.home_view.IHomeViewRequire;
import UI.components.search_bar.ISearchBar;
import UI.components.search_bar.ISearchBarRequire;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.svg.SVGGlyph;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SearchBarController extends Component implements ISearchBar {

    private ISearchBarRequire required;

    @FXML
    private ScrollPane scrollPane;

    public SearchBarController() {
        super("search_bar.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    @Override
    public void setRequired(ISearchBarRequire required) {
        this.required = required;
    }
}
