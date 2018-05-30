package UI.components.elucidation_view.theme;

import ACQ.IEventListener;
import ACQ.ThemeEnum;
import UI.components.Component;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
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

    @FXML
    private JFXTextArea documentationTextArea;

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

        documentationTextArea.setPrefRowCount(4);

        themeComboBox.setOnAction(getVerifyDataEventHandler());
        subthemeField.textProperty().addListener(getVerifyDataChangeListener());
        levelOfFunctionComboBox.setOnAction(getVerifyDataEventHandler());
        documentationTextArea.textProperty().addListener(getVerifyDataChangeListener());
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
    public int getLevelOfFunction() {
        if (levelOfFunctionComboBox.getValue() != null) {
            return levelOfFunctionComboBox.getValue();
        } else {
            return -1;
        }
    }

    @Override
    public String getDocumentation() {
        return documentationTextArea.getText();
    }

    @Override
    public void setTheme(ThemeEnum theme) {
        this.themeComboBox.setValue(theme);
    }

    @Override
    public void setSubtheme(String subtheme) {
        subthemeField.setText(subtheme);
    }

    @Override
    public void setLevelOfFunction(int levelOfFunction) {
        levelOfFunctionComboBox.setValue(levelOfFunction);
    }

    @Override
    public void setDocumentation(String documentation) {
        documentationTextArea.setText(documentation);
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

        if (getLevelOfFunction() == -1) {
            addError(levelOfFunctionComboBox, errorClass);
            hasData = false;
        } else {
            removeError(levelOfFunctionComboBox, errorClass);
        }

        if (getDocumentation().length() == 0) {
            addError(documentationTextArea, errorClass);
            hasData = false;
        } else {
            removeError(documentationTextArea, errorClass);
        }

        return hasData;
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

    private <T> ChangeListener<T> getVerifyDataChangeListener() {
        return (observableValue, t, t1) -> verifyData();
    }

    private <T extends Event> EventHandler<T> getVerifyDataEventHandler() {
        return t -> verifyData();
    }

}

