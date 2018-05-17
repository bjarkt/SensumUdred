package BLL.theme_manager;

import BLL.ACQ.ThemeEnum;
import BLL.open_case.ICase;

import java.util.Collection;

public interface IThemeManager {
    /**
     * Array of ThemeEnums
     */
    ThemeEnum[] themes = ThemeEnum.values();

    /**
     * Add a new theme to the ThemeManager and the Case
     * @param theme a themeEnum
     * @param documentation documentation string
     */
    void addNewTheme(ThemeEnum theme, String documentation);

    /**
     * Get the current themes
     * @return current themes
     */
    Collection<Theme> getCurrentThemes();

    /**
     * Get the case associated with the theme manager
     * @return the case
     */
    ICase getCase();
}
