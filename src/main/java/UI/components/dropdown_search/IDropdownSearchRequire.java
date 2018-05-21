package UI.components.dropdown_search;

import com.jfoenix.controls.JFXListCell;

public interface IDropdownSearchRequire<T> {

    JFXListCell<T> getCellFactory();

}
