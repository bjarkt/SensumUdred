package BLL.theme_manager;

import BLL.ACQ.ThemeEnum;
import BLL.open_case.ICase;

import java.util.LinkedHashSet;
import java.util.Set;

public class ThemeManager implements IThemeManager {
    private ICase theCase;
    private Set<Theme> currentThemes;

    public ThemeManager(ICase aCase) {
        this.theCase = aCase;
        currentThemes = new LinkedHashSet<>(); // Using linked hash set, so the order of insertion is preserved
    }

    /**
     * {@inheritDoc}
     */
    public void addNewTheme(ThemeEnum theme, String subtheme, String documentation) {
        Theme newTheme = new Theme(theme, subtheme, documentation);
        this.theCase.addThemes(newTheme);
        this.currentThemes.add(newTheme);
    }

    /**
     * {@inheritDoc}
     */
    public Set<Theme> getCurrentThemes() {
        return currentThemes;
    }

    /**
     * {@inheritDoc}
     */
    public ICase getCase() {
        return theCase;
    }
}
