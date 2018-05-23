package UI.components.elucidation_view.theme;

import ACQ.IEventListener;
import ACQ.ThemeEnum;
import UI.components.IComponent;

public interface IThemeUI extends IComponent {

    void onThemeSelected(IEventListener<IThemeUI> listener);

    boolean isSelected();

    ThemeEnum getTheme();

    String getSubtheme();

    Integer getLevelOfFunction();

    String getDocumentation();

    /**
     * verify that it has data
     * @return true, if none of the fields are empty.
     */
    boolean verifyData();

}
