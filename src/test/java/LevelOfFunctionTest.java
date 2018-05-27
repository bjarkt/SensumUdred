import ACQ.ITheme;
import BLL.Inquiry.Inquiry;
import BLL.open_case.Case;
import BLL.open_case.ICase;
import BLL.theme_manager.IThemeManager;
import BLL.theme_manager.ThemeManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    public void setTotalLevelOfFunctionTest() {
        ICase aCase = createCase();

        char[] badFunctionLevels = {'@', 'F'};
        char goodFunctionLevel = 'A';

        try {
            aCase.setTotalLevelOfFunction(badFunctionLevels[0]);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException iae) {
            assertEquals(iae.getMessage(), "level must be between A and E, it was " + badFunctionLevels[0]);
        }

        try {
            aCase.setTotalLevelOfFunction(badFunctionLevels[1]);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException iae) {
            assertEquals(iae.getMessage(), "level must be between A and E, it was " + badFunctionLevels[1]);
        }

        aCase.setTotalLevelOfFunction(goodFunctionLevel);

        assertEquals(goodFunctionLevel, aCase.getTotalLevelOfFunction());

    }

    @Test
    public void illegalArgumentTest() {
        Set<ITheme> themeSet = themeManager.getCase().getThemes();
        List<ITheme> themeList = new ArrayList<>(themeSet);

        int[] badFunctionLevels = {-1, 5};

        try {
            themeList.get(0).setLevelOfFunction(badFunctionLevels[0]);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException iae) {
            assertEquals(iae.getMessage(), "levelOfFunction must be between 0 and 4, it was " + badFunctionLevels[0]);
        }

        try {
            themeList.get(0).setLevelOfFunction(badFunctionLevels[1]);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException iae) {
            assertEquals(iae.getMessage(), "levelOfFunction must be between 0 and 4, it was " + badFunctionLevels[1]);
        }

        assertEquals(-1, themeList.get(0).getLevelOfFunction());
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