package BLL.ACQ;

public interface ITheme {
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
}
