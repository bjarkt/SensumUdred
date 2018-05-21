package BLL.theme_manager;

import ACQ.ThemeEnum;
import BLL.open_case.ICase;

import java.util.Set;

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
    void addNewTheme(ThemeEnum theme, String subtheme, String documentation);

    /**
     * Get the current themes
     * @return current themes
     */
    Set<Theme> getCurrentThemes();

    /**
     * Get the case associated with the theme manager
     * @return the case
     */
    ICase getCase();
}
