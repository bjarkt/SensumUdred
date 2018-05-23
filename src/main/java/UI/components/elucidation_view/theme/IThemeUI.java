package UI.components.elucidation_view.theme;

import ACQ.IEventListener;
import UI.components.IComponent;

public interface IThemeUI extends IComponent {

    void onThemeSelected(IEventListener<IThemeUI> listener);

    boolean isSelected();

}
