package UI.components.home_view;

import UI.components.Component;
import UI.components.IEventListener;
import UI.components.home_view.IHomeView;
import UI.components.home_view.IHomeViewRequire;
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
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeViewController extends Component implements IHomeView {

    private IHomeViewRequire required;

    @FXML
    private JFXScrollPane scrollPane;

    public HomeViewController() {
        super("home_view.fxml", "Hjem");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        StackPane container = new StackPane();
        container.setPadding(new Insets(30));

        scrollPane.setContent(container);

        JFXButton button = new JFXButton("");
        SVGGlyph arrow = new SVGGlyph(0,
                "FULLSCREEN",
                "M402.746 877.254l-320-320c-24.994-24.992-24.994-65.516 0-90.51l320-320c24.994-24.992 65.516-24.992 90.51 0 24.994 24.994 "
                        + "24.994 65.516 0 90.51l-210.746 210.746h613.49c35.346 0 64 28.654 64 64s-28.654 64-64 64h-613.49l210.746 210.746c12.496 "
                        + "12.496 18.744 28.876 18.744 45.254s-6.248 32.758-18.744 45.254c-24.994 24.994-65.516 24.994-90.51 0z",
                Color.WHITE);
        arrow.setSize(20, 16);
        button.setGraphic(arrow);
        button.setRipplerFill(Color.WHITE);
        scrollPane.getTopBar().getChildren().add(button);

        Label title = new Label("Title");
        scrollPane.getBottomBar().getChildren().add(title);
        title.setStyle("-fx-text-fill:WHITE; -fx-font-size: 40;");
       // JFXScrollPane.smoothScrolling((ScrollPane) scrollPane.getChildren().get(0));


        scrollPane.getMainHeader().getStyleClass().add("homeview_header");
        scrollPane.getMainHeader().setEffect(new DropShadow(30.0,0,3.0,Color.rgb(28,90,252, 0.2)));
        scrollPane.getContent().getStyleClass().add("homeview_content");



    }

    @Override
    public void setRequired(IHomeViewRequire required) {
        this.required = required;
    }
}
