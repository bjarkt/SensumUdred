package ACQ;

public enum ThemeEnum {
    FYSISK_FUNKTIONS_NEDSAETTELSE("Fysisk funktionsnedsættelse", "Funktionsnedsættelse i kroppens anatomi eller kroppens funktioner, eksklusiv de mentale funktioner."),
    PSYKISK_FUNKTIONS_NEDSAETTELSE("Psykisk funktionsnedsættelse", "Funktionsnedsættelse i de mentale funktioner."),
    SOCIALT_PROBLEM("Socialt problem", "Tilstand, som er kendetegnet ved, at en person er, eller er i fare for at blive, marginaliseret."),
    PRAKTISKE_OPGAVER_I_HJEMMET("Praktiske opgaver i hjemmet", "Aktivitet, der vedrører huslige og andre dagligdags handlinger og opgaver i relation til husførelse."),
    EGENOMSORG("Egenomsorg", "Aktivitet, der vedrører praktiske og hygiejnemæssige handlinger i relation til personen selv."),
    SOCIALT_LIV("Socialt liv", "Aktivitet, der vedrører relationer til andre mennesker."),
    SUNDHED("Sundhed", "Tilstand af fuldstændig fysisk, psykisk og social trivsel."),
    KOMMUNIKATION("Kommunikation", "Proces, der består af en overførsel eller udveksling af information."),
    MOBILITET("Mobilitet", "Aktivitet, der vedrører bevægelse og færden."),
    SAMFUNDSLIV("Samfundsliv", "Forhold, der vedrører bolig samt de opgaver og handlinger, som er nødvendige for at deltage i undervisning og beskæftigelse og for at gennemføre økonomiske transaktioner."),
    OMGIVELSER("Omgivelser", "Kontekstuel faktor, der omfatter de fysiske, sociale og holdningsmæssige omgivelser, som en person bor og lever i.");

    private String name;
    private String definition;

    ThemeEnum(String name, String definition) {
        this.name = name;
        this.definition = definition;
    }

    public String getName() {
        return name;
    }

    public String getDefinition() {
        return definition;
    }

    public static ThemeEnum fromString(String name) {
        ThemeEnum themeEnum = null;
        for (ThemeEnum theme : values()) {
            if (theme.name.equals(name)) {
                themeEnum = theme;
                break;
            }
        }
        return themeEnum;
    }

    @Override
    public String toString() {
        return "ThemeEnum{" +
                "name='" + name + '\'' +
                ", definition='" + definition + '\'' +
                '}';
    }
}

