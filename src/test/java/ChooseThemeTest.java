import BLL.ACQ.ITheme;
import BLL.Inquiry.Inquiry;
import BLL.open_case.Case;
import BLL.open_case.ICase;
import BLL.theme_manager.IThemeManager;
import BLL.theme_manager.Theme;
import BLL.theme_manager.ThemeManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChooseThemeTest {

    private IThemeManager themeManager;

    @BeforeEach
    private void initialize() {
        themeManager = new ThemeManager(createCase());
    }

    @Test
    public void addThemeTest() {
        themeManager.addNewTheme(IThemeManager.themes[0], "Hørenedsættelse", "Bente har hørenedsættelse, det er lidt træls");
        themeManager.addNewTheme(IThemeManager.themes[1], "Angst", "Bente har også angst, det er meget træls!");

        assert themeManager.getCurrentThemes().stream().findFirst().isPresent();
        Theme firstTheme = themeManager.getCurrentThemes().stream().findFirst().get();

        assertEquals("inquiry description", themeManager.getCase().getDescription());
        assertEquals(firstTheme.getTheme(), IThemeManager.themes[0]);
        assertEquals(firstTheme.getSubtheme(), "Hørenedsættelse");
        assertEquals(firstTheme.getDocumentation(), "Bente har hørenedsættelse, det er lidt træls");
    }

    private ICase createCase() {
        return new Case(new Inquiry() {
            @Override
            public String getDescription() {
                return "inquiry description";
            }
        });
    }
}