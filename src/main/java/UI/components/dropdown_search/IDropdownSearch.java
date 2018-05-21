package UI.components.dropdown_search;

import ACQ.IUser;
import UI.components.IComponent;
import javafx.collections.ObservableList;

import java.util.List;

public interface IDropdownSearch<T> extends IComponent {

    void setRequired(IDropdownSearchRequire required);

    void updateList(List<T> searchResults);

}
