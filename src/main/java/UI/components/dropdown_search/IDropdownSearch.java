package UI.components.dropdown_search;

import ACQ.IEventListener;
import UI.components.IComponent;

import java.util.Set;

public interface IDropdownSearch<T> extends IComponent {

    void setRequired(IDropdownSearchRequire required);

    void updateList(Set<T> searchResults);

    void onType(IEventListener<String> listener);

    void onDone(IEventListener<Set<T>> listIEventListener);

    void expand();

    void collapse();

}
