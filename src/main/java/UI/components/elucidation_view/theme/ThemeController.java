package UI.components.elucidation_view.theme;

import ACQ.IEventListener;
import ACQ.ITheme;
import ACQ.ThemeEnum;
import BLL.theme_manager.IThemeManager;
import UI.components.Component;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ThemeController extends Component implements IThemeUI {

    private List<IEventListener<IThemeUI>> onThemeSelectedSubscribers = new ArrayList<>();

    private boolean selected;

    @FXML
    private AnchorPane theme_container;

    @FXML
    private JFXComboBox<ThemeEnum> themeComboBox;

    @FXML
    private JFXTextField subthemeField;

    @FXML
    private JFXCheckBox themeCheckbox;


    public ThemeController() {
        super("theme.fxml");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<ThemeEnum> comboBoxOptions = FXCollections.observableArrayList(ThemeEnum.values());
        themeComboBox.getItems().addAll(comboBoxOptions);

        themeComboBox.setCellFactory(
                new Callback<ListView<ThemeEnum>, ListCell<ThemeEnum>>() {
                    @Override public ListCell<ThemeEnum> call(ListView<ThemeEnum> param) {
                        final ListCell<ThemeEnum> cell = new ListCell<ThemeEnum>() {
                            @Override public void updateItem(ThemeEnum item, boolean empty) {
                                super.updateItem(item, empty);
                                if (item != null) {
                                    setText(item.getName());
                                }
                                else {
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                });
    }

    @Override
    public void onThemeSelected(IEventListener<IThemeUI> listener) {
        onThemeSelectedSubscribers.add(listener);
    }


    @FXML
    void checkBoxAction(ActionEvent event) {
        if(selected) selected = false;
        else selected = true;
        onThemeSelectedSubscribers.forEach(listener -> listener.onAction(this));
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    public ThemeEnum getTheme(){
        return themeComboBox.getValue();
    }

    public String getSubtheme(){
        return subthemeField.getText();
    }



}

