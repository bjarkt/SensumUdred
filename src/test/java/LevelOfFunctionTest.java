import BLL.ACQ.ITheme;
import BLL.Inquiry.Inquiry;
import BLL.open_case.Case;
import BLL.open_case.ICase;
import BLL.theme_manager.IThemeManager;
import BLL.theme_manager.Theme;
import BLL.theme_manager.ThemeManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class LevelOfFunctionTest {

    private IThemeManager themeManager;

    @BeforeEach
    private void initialize() {
        themeManager = new ThemeManager(createCase());
        addThemes();
    }

    @Test
    public void setLevelOfFunctionTest() {
        Set<ITheme> themeSet = themeManager.getCase().getThemes();
        List<ITheme> themeList = new ArrayList<>(themeSet);

        themeList.get(0).setLevelOfFunction(4);
        themeList.get(1).setLevelOfFunction(2);

        List<Integer> addedLevelsOfFunction = new ArrayList<>();
        for (ITheme theme : themeManager.getCase().getThemes()) {
            addedLevelsOfFunction.add(theme.getLevelOfFunction());
        }
        Collections.sort(addedLevelsOfFunction);

        assertEquals(2, (int)addedLevelsOfFunction.get(0));
        assertEquals(4, (int)addedLevelsOfFunction.get(1));
    }

    @Test
    public void illegalArgumentTest() {
        Set<ITheme> themeSet = themeManager.getCase().getThemes();
        List<ITheme> themeList = new ArrayList<>(themeSet);

        int[] badFunctionLevels = {-1, 5};

        try {
            themeList.get(0).setLevelOfFunction(badFunctionLevels[0]);
            fail("Expected an IndexOutOfBoundsException to be thrown");
        } catch (IllegalArgumentException iae) {
            assertEquals(iae.getMessage(), "levelOfFunction must be between 0 and 4, it was " + badFunctionLevels[0]);
        }
    }

    private void addThemes() {
        themeManager.addNewTheme(IThemeManager.themes[0], "Hørenedsættelse", "Bente har hørenedsættelse, det er lidt træls");
        themeManager.addNewTheme(IThemeManager.themes[1], "Angst", "Bente har også angst, det er meget træls!");
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