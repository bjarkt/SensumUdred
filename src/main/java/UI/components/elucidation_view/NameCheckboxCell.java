package UI.components.elucidation_view;

import ACQ.IUser;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXListCell;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
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
        this.setOnMouseClicked(event -> {
            if(checked == true)  {
                checkBox.setSelected(false);
                checked = false;
                this.updateSelected(false);
            } else if(checked == false) {
                checkBox.setSelected(true);
                checked = true;
                this.updateSelected(true);
            }
        });
    }

    @Override
    protected void updateItem(Object item, boolean empty) {
        super.updateItem(item, empty);
        setGraphic(null);
        setText(null);
        if(item != null){
            citizenName.setText(((IUser)item).getFirstName() + " " + ((IUser)item).getLastName());
            id.setText(((IUser)item).getAccount().getUsername());
            this.getChildren().add(new Label(((IUser)item).getAccount().getUsername()));
            setGraphic(hBox);
        }
    }
}
