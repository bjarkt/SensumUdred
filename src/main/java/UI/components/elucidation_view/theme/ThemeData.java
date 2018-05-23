package UI.components.elucidation_view.theme;

import ACQ.ThemeEnum;

public class ThemeData {
    private ThemeEnum themeEnum;
    private String subtheme;
    private Integer levelOfFunction;
    private String documentation;

    public ThemeData(ThemeEnum themeEnum, String subtheme, Integer levelOfFunction, String documentation) {
        this.themeEnum = themeEnum;
        this.subtheme = subtheme;
        this.levelOfFunction = levelOfFunction;
        this.documentation = documentation;
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

    public String getDocumentation() {
        return documentation;
    }

    @Override
    public String toString() {
        return "ThemeData{" +
                "themeEnum=" + themeEnum +
                ", subtheme='" + subtheme + '\'' +
                ", levelOfFunction=" + levelOfFunction +
                ", documentation='" + documentation + '\'' +
                '}';
    }
}
