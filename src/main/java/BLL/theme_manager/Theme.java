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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Theme theme1 = (Theme) o;
        return theme == theme1.theme &&
                Objects.equals(documentation, theme1.documentation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(theme, documentation);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(Theme theme) {
        return Integer.compare(this.theme.ordinal(), theme.theme.ordinal());
    }
}
