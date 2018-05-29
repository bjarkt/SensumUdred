import ACQ.ICase;
import ACQ.ITheme;
import BLL.Inquiry.Inquiry;
import BLL.open_case.Case;
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
        Case aCase = (Case) createCase();

        char[] badFunctionLevels = {'@', 'F'};
        char goodFunctionLevel = 'A';

        for (char badFunctionLevel : badFunctionLevels) {
            try {
                aCase.setTotalLevelOfFunction(badFunctionLevel);
                fail("Expected an IllegalArgumentException to be thrown");
            } catch (IllegalArgumentException iae) {
                assertEquals(iae.getMessage(), "level must be between A and E, it was " + badFunctionLevel);
            }
        }

        aCase.setTotalLevelOfFunction(goodFunctionLevel);

        assertEquals(goodFunctionLevel, aCase.getTotalLevelOfFunction());
    }

    @Test
    public void illegalArgumentTest() {
        Set<ITheme> themeSet = themeManager.getCase().getThemes();
        List<ITheme> themeList = new ArrayList<>(themeSet);

        int[] badFunctionLevels = {-1, 5};

        for (int badFunctionLevel : badFunctionLevels) {
            try {
                themeList.get(0).setLevelOfFunction(badFunctionLevel);
                fail("Expected an IllegalArgumentException to be thrown");
            } catch (IllegalArgumentException iae) {
                assertEquals(iae.getMessage(), "levelOfFunction must be between 0 and 4, it was " + badFunctionLevel);
            }
        }

        assertEquals(-1, themeList.get(0).getLevelOfFunction());
    }

    private void addThemes() {
        themeManager.addNewTheme(IThemeManager.themes[0], "Hørenedsættelse", "Bente har hørenedsættelse, det er lidt træls");
        themeManager.addNewTheme(IThemeManager.themes[1], "Angst", "Bente har også angst, det er meget træls!");
    }

    private ICase createCase() {
        return new Case(new Inquiry("Description", "Source"));
    }
}