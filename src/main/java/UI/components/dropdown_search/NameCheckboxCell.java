package UI.components.dropdown_search;

import ACQ.IProfile;
import ACQ.IUser;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXListCell;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public abstract class NameCheckboxCell extends JFXListCell{

    HBox hBox = new HBox();
    VBox vBox = new VBox();
    JFXCheckBox checkBox = new JFXCheckBox();
    Label citizenName = new Label("");
    Label id = new Label("");
    Region spacer = new Region();
    boolean checked;

    public NameCheckboxCell() {
        super();
        vBox.getChildren().addAll(citizenName, id);
        vBox.setAlignment(Pos.CENTER_LEFT);
        hBox.getChildren().addAll(vBox, spacer, checkBox);
        citizenName.getStyleClass().add("listView_primaryText");
        id.getStyleClass().add("listView_secondaryText");
        hBox.setHgrow(spacer, Priority.ALWAYS);
        hBox.setAlignment(Pos.CENTER_LEFT);
        this.cellRippler.setRipplerFill(Color.rgb(42, 112, 226, 0.7));

        this.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue) checkBox.setSelected(true);
            else checkBox.setSelected(false);
        });


    }


    @Override
    protected void updateItem(Object item, boolean empty) {
        super.updateItem(item, empty);
        setGraphic(null);
        setText(null);
        if(item != null){
            citizenName.setText(((IUser)item).getName());
            id.setText(((IUser)item).getEmail());
            this.getChildren().add(new Label(((IUser)item).getEmail()));
            setGraphic(hBox);
        }
    }
}
