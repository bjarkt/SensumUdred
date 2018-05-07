package UI.components.home_view;

import UI.components.Component;
import UI.components.IEventListener;
import UI.components.home_view.IHomeView;
import UI.components.home_view.IHomeViewRequire;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXScrollPane;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeViewController extends Component implements IHomeView {

    private IHomeViewRequire required;

    @FXML
    private JFXScrollPane scrollPane;

    private FlowPane content;

    public HomeViewController() {
        super("home_view.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        scrollPane = new JFXScrollPane();
        content = new FlowPane();
        scrollPane.getTopBar().getChildren().add(new JFXButton("Dette er en knap"));
        scrollPane.getBottomBar().getChildren().add(new Label("Dette er en titel"));
        scrollPane.setContent(content);
        content.getChildren().add(new JFXButton("TESTER"));

    }

    @Override
    public void setRequired(IHomeViewRequire required) {
        this.required = required;
    }
}
