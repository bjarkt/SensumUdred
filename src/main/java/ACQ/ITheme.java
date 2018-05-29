package ACQ;

public interface ITheme extends Comparable<ITheme>  {
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
     * Get the level of function for this theme
     * @return level of function
     */
    int getLevelOfFunction();

    /**
     * Set the level of function, must be in range [0,4]
     * @throws IllegalArgumentException throws when argument is out of range
     */
    void setLevelOfFunction(int levelOfFunction) throws IllegalArgumentException;

    /**
     * Compare a theme using the ThemeEnum ordinal.
     * @param theme other theme
     * @return compareTo int
     */
    int compareTo(ITheme theme);

}
