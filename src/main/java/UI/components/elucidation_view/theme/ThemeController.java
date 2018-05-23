package UI.components.elucidation_view.theme;

import ACQ.IEventListener;
import ACQ.ITheme;
import ACQ.ThemeEnum;
import BLL.theme_manager.IThemeManager;
import UI.components.Component;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.StringConverter;

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
    private JFXComboBox<Integer> levelOfFunctionComboBox;

    @FXML
    private JFXTextField subthemeField;

    @FXML
    private JFXCheckBox themeCheckbox;

    private String errorClass = "theme-input-error";


    public ThemeController() {
        super("theme.fxml");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<ThemeEnum> comboBoxOptions = FXCollections.observableArrayList(ThemeEnum.values());
        this.initThemeEnumComboBox(comboBoxOptions);

        ObservableList<Integer> levelOfFunctionOptions = FXCollections.observableArrayList(0, 1, 2, 3, 4);
        levelOfFunctionComboBox.getItems().addAll(levelOfFunctionOptions);

        themeComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ThemeEnum>() {
            @Override
            public void changed(ObservableValue<? extends ThemeEnum> observableValue, ThemeEnum themeEnum, ThemeEnum t1) {
                verifyData(themeComboBox);
            }
        });

        subthemeField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                verifyData(subthemeField);
            }
        });

        levelOfFunctionComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                verifyData(levelOfFunctionComboBox);
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

    @Override
    public ThemeEnum getTheme(){
        return themeComboBox.getValue();
    }

    @Override
    public String getSubtheme(){
        return subthemeField.getText();
    }

    @Override
    public Integer getLevelOfFunction() {
        return levelOfFunctionComboBox.getValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean verifyData() {
        boolean hasData = true;

        if (getTheme() == null) {
            addError(themeComboBox, errorClass);
            hasData = false;
        } else {
            removeError(themeComboBox, errorClass);
        }

        if (getSubtheme().length() == 0) {
            addError(subthemeField, errorClass);
            hasData = false;
        } else {
            removeError(subthemeField, errorClass);
        }

        if (getLevelOfFunction() == null) {
            addError(levelOfFunctionComboBox, errorClass);
            hasData = false;
        } else {
            removeError(levelOfFunctionComboBox, errorClass);
        }

        return hasData;
    }

    private boolean verifyData(JFXComboBox comboBox) {
        boolean missingData = true;

        if (comboBox.getValue() == null) {
            addError(comboBox, errorClass);
            missingData = false;
        } else {
            removeError(comboBox, errorClass);
        }

        return missingData;
    }

    private boolean verifyData( JFXTextField textField) {
        boolean missingData = true;

        if (textField.getText().length() == 0) {
            addError(textField, errorClass);
            missingData = false;
        } else {
            removeError(textField, errorClass);
        }

        return missingData;
    }

    private void initThemeEnumComboBox(ObservableList<ThemeEnum> comboBoxOptions) {
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

        themeComboBox.setConverter(new StringConverter<ThemeEnum>() {
            @Override
            public String toString(ThemeEnum object) {
                return object.getName();
            }

            @Override
            public ThemeEnum fromString(String string) {
                return ThemeEnum.fromString(string);
            }
        });
    }

    private void addError(Node node, String errorClass) {
        node.getStyleClass().add(errorClass);
    }

    private void removeError(Node node, String errorClass) {
        node.getStyleClass().remove(errorClass);
    }

}

