package UI.components.elucidation_view.theme;

import ACQ.ThemeEnum;

public class ThemeData {
    private ThemeEnum themeEnum;
    private String subtheme;
    private Integer levelOfFunction;

    public ThemeData(ThemeEnum themeEnum, String subtheme, Integer levelOfFunction) {
        this.themeEnum = themeEnum;
        this.subtheme = subtheme;
        this.levelOfFunction = levelOfFunction;
    }

    public ThemeEnum getThemeEnum() {
        return themeEnum;
    }

    public String getSubtheme() {
        return subtheme;
    }

    public Integer getLevelOfFunction() {
        return levelOfFunction;
    }

    @Override
    public String toString() {
        return "ThemeData{" +
                "themeEnum=" + themeEnum +
                ", subtheme='" + subtheme + '\'' +
                ", levelOfFunction=" + levelOfFunction +
                '}';
    }
}
