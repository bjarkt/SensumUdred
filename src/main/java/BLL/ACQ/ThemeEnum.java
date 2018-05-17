package BLL.ACQ;

public enum ThemeEnum {
    FYSISK_FUNKTIONS_NEDSAETTELSE("Fysisk funktionsnedsættelse"),
    PSYKISK_FUNKTIONS_NEDSAETTELSE("Psykisk funktionsnedsættelse"),
    SOCIALT_PROBLEM("Socialt problem"),
    PRAKTISKE_OPGAVER_I_HJEMMET("Praktiske opgaver i hjemmet"),
    EGENOMSORG("Egenomsorg"),
    SOCIALT_LIV("Socialt liv"),
    SUNDHED("Sundhed"),
    KOMMUNIKATION("Kommunikation"),
    MOBILITET("Mobilitet"),
    SAMFUNDSLIV("Samfundsliv"),
    OMGIVELSER("Omgivelser");

    private String description;

    ThemeEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "ThemeEnum{" +
                "description='" + description + '\'' +
                '}';
    }
}
