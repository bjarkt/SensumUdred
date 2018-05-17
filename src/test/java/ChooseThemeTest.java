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
    public void test() {
        themeManager.addNewTheme(IThemeManager.themes[0], "Bla bla dok tekst");
        themeManager.addNewTheme(IThemeManager.themes[1], "Mere dokumentation for resourcer");

        assertEquals("inquiry description", themeManager.getCase().getDescription());
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