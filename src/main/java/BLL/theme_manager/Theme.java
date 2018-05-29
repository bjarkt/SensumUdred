package BLL.theme_manager;

import ACQ.ITheme;
import ACQ.ThemeEnum;

import java.util.Objects;

public class Theme implements ITheme{
    private ThemeEnum theme;
    private String subtheme;
    private String documentation;
    private int levelOfFunction;

    public Theme(ThemeEnum theme, String subtheme, String documentation) {
        this.theme = theme;
        this.subtheme = subtheme;
        this.documentation = documentation;
        this.levelOfFunction = -1;
    }

    /**
     * {@inheritDoc}
     */
    public ThemeEnum getTheme() {
        return theme;
    }

    /**
     * {@inheritDoc}
     */
    public String getDocumentation() {
        return documentation;
    }

    /**
     * {@inheritDoc}
     */
    public String getSubtheme() {
        return subtheme;
    }

    /**
     * {@inheritDoc}
     */
    public int getLevelOfFunction() {
        return levelOfFunction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLevelOfFunction(int levelOfFunction) throws IllegalArgumentException {
        if (levelOfFunction < 0 || levelOfFunction > 4) {
            throw new IllegalArgumentException("levelOfFunction must be between 0 and 4, it was " + levelOfFunction);
        }

        this.levelOfFunction = levelOfFunction;
    }

    /**
     * Checks if two themes are equal to each other.
     * @param o any object
     * @return true, if they are equal; otherwise false
     */
    @Override
    public boolean equals(Object o) {
        boolean equal;

        if(this == o) {
            equal = true;
        } else if (o == null || getClass() != o.getClass()) {
            equal = false;
        } else {
            Theme theme1 = (Theme) o;
            equal = theme == theme1.theme && Objects.equals(documentation, theme1.documentation);
        }

        return equal;
    }

    /**
     * Create an unique hash based on the object.
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(theme, documentation);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(ITheme theme) {
        return Integer.compare(this.theme.ordinal(), theme.getTheme().ordinal());
    }
}
