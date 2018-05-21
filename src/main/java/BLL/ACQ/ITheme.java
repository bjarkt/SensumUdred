package BLL.ACQ;

import BLL.theme_manager.Theme;

public interface ITheme extends Comparable<Theme>  {
    /**
     * Get the theme
     * @return theme enum
     */
    ThemeEnum getTheme();

    /**
     * Get the documentation string
     * @return documentation string
     */
    String getDocumentation();

    /**
     * Get the subtheme
     * @return subtheme
     */
    String getSubtheme();

    /**
     * Compare a theme using the ThemeEnum ordinal.
     * @param theme other theme
     * @return compareTo int
     */
    int compareTo(Theme theme);

}
