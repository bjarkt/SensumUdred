package UI.components.elucidation_view.theme;

import ACQ.IEventListener;
import ACQ.ITheme;
import ACQ.ThemeEnum;
import BLL.theme_manager.Theme;
import UI.components.IComponent;

public interface IThemeUI extends IComponent {

    void onThemeSelected(IEventListener<IThemeUI> listener);

    boolean isSelected();

    ThemeEnum getTheme();

    String getSubtheme();

    int getLevelOfFunction();

    String getDocumentation();

    void setTheme(ThemeEnum theme);
    void setSubtheme(String subtheme);
    void setLevelOfFunction(int levelOfFunction);
    void setDocumentation(String documentation);

    /**
     * verify that it has data
     * @return true, if none of the fields are empty.
     */
    boolean verifyData();

}
