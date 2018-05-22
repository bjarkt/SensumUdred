package UI.components.dropdown_search;

import ACQ.IUser;
import UI.components.IComponent;
import UI.components.IEventListener;

import java.util.List;
import java.util.Set;

public interface IDropdownSearch<T> extends IComponent {

    void setRequired(IDropdownSearchRequire required);

    void updateList(List<T> searchResults);

    void onType(IEventListener<String> listener);

    void onDone(IEventListener<Set<T>> listIEventListener);

    void expand();

    void collapse();

}
