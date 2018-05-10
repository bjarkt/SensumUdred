package UI.components.search_bar;

import UI.components.IComponent;
import UI.components.IEventListener;

public interface ISearchBar extends IComponent {
    void setRequired(ISearchBarRequire required);

    void onType(IEventListener<String> listener);

}
