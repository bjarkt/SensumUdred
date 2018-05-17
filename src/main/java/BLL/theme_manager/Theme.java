package BLL.theme_manager;

import BLL.ACQ.ITheme;
import BLL.ACQ.ThemeEnum;

import java.util.Objects;

public class Theme implements ITheme {
    private ThemeEnum theme;
    private String documentation;

    public Theme(ThemeEnum theme, String documentation) {
        this.theme = theme;
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
}
