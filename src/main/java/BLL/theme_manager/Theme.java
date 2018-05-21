package BLL.theme_manager;

import BLL.ACQ.ITheme;
import BLL.ACQ.ThemeEnum;

import java.util.Objects;

public class Theme implements ITheme, Comparable<Theme> {
    private ThemeEnum theme;
    private String subtheme;
    private String documentation;

    public Theme(ThemeEnum theme, String subtheme, String documentation) {
        this.theme = theme;
        this.subtheme = subtheme;
        this.documentation = documentation;
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
     * Compare a theme using the ThemeEnum ordinal.
     * @param theme other theme
     * @return compareTo int
     */
    @Override
    public int compareTo(Theme theme) {
        return Integer.compare(this.theme.ordinal(), theme.theme.ordinal());
    }
}
