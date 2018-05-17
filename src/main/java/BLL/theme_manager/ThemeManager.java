package BLL.theme_manager;

import BLL.ACQ.ThemeEnum;
import BLL.open_case.ICase;

import java.util.Collection;
import java.util.HashSet;

public class ThemeManager implements IThemeManager {
    private ICase theCase;
    private Collection<Theme> currentThemes;

    public ThemeManager(ICase aCase) {
        this.theCase = aCase;
        currentThemes = new HashSet<>();
    }

    /**
     * {@inheritDoc}
     */
    public void addNewTheme(ThemeEnum theme, String documentation) {
        Theme newTheme = new Theme(theme, documentation);
        this.theCase.addThemes(newTheme);
        this.currentThemes.add(newTheme);
    }

    /**
     * {@inheritDoc}
     */
    public Collection<Theme> getCurrentThemes() {
        return currentThemes;
    }

    /**
     * {@inheritDoc}
     */
    public ICase getCase() {
        return theCase;
    }
}
