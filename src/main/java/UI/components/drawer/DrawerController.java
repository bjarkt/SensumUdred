package UI.components.drawer;

import UI.components.Component;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;

import java.net.URL;
import java.util.ResourceBundle;

public class DrawerController extends Component implements IDrawer {

    private IDrawerRequire required;

    private JFXButton closeDrawer;

    private FlowPane drawerPane;

    private Parent content;

    private JFXDrawer.DrawerDirection direction;

    @FXML
    private JFXDrawer drawer;

    public DrawerController(IDrawerRequire required, JFXDrawer.DrawerDirection direction, String title) {
        super("drawer.fxml",title);
        setRequired(required);
        this.direction = direction;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        drawer.setDirection(direction);

        drawerPane = new FlowPane();
        drawerPane.setAlignment(Pos.TOP_CENTER);
        drawerPane.setOrientation(Orientation.VERTICAL);


        closeDrawer = new JFXButton();
        SVGPath svgPath = new SVGPath();
        svgPath.setContent("M19 6.41L17.59 5 12 10.59 6.41 5 5 6.41 10.59 12 5 17.59 6.41 19 12 13.41 17.59 19 19 17.59 13.41 12z");
        svgPath.setFill(Color.GRAY);
        closeDrawer.setGraphic(svgPath);
        closeDrawer.getStyleClass().add("raised-button");
        Label menuTitle = new Label(getBreadcrumb().getValue());
        menuTitle.getStyleClass().add("containerTop_label");
        Region menuSpacer = new Region();
        HBox drawerTop = new HBox(menuTitle, menuSpacer, closeDrawer);
        drawerTop.setHgrow(menuSpacer, Priority.ALWAYS);
        drawerTop.setAlignment(Pos.CENTER_RIGHT);
        drawerTop.setPrefWidth(drawer.getDefaultDrawerSize());
        drawerTop.getStyleClass().add("drawer_container--top");

        drawerPane.getChildren().addAll(drawerTop);
        drawer.setSidePane(drawerPane);
        drawer.setOverLayVisible(true);
        drawer.setResizableOnDrag(false);
        drawer.onDrawerClosedProperty().set(event -> {
            if(required.getParent().getChildren().contains(this.getView())){
                required.getParent().getChildren().remove(this.getView());
                clearContent();
            }
        });

        closeDrawer.onActionProperty().set(event -> {
            drawer.close();
        });

    }

    @Override
    public void open() {
        required.getParent().getChildren().add(this.getView());
        required.getParent().setTopAnchor(this.getView(), .0);
        required.getParent().setLeftAnchor(this.getView(), .0);
        required.getParent().setRightAnchor(this.getView(), .0);
        required.getParent().setBottomAnchor(this.getView(), .0);
        drawer.open();
        setContent();
    }

    @Override
    public void close() {
        if(drawer != null) drawer.close();
    }

    @Override
    public void setRequired(IDrawerRequire required) {
        this.required = required;
    }

    @Override
    public void setContent() {
        this.content = required.getContent();
        if(content != null && !drawerPane.getChildren().contains(content)) drawerPane.getChildren().add(required.getContent());
    }

    @Override
    public void clearContent() {
        if(content != null && drawerPane.getChildren().contains(content)) drawerPane.getChildren().remove(content);
    }
}
