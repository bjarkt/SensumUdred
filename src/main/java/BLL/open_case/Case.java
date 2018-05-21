package BLL.open_case;

import BLL.ACQ.ElucidationState;
import BLL.ACQ.ITheme;
import BLL.ACQ.IUser;
import BLL.Inquiry.IInquiry;
import BLL.Inquiry.Inquiry;
import BLL.theme_manager.Theme;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Case extends Inquiry implements ICase {

    // Set of caseworkers on the case.
    private Set<IUser> caseworkers;

    // Citizen in focus.
    private IUser citizen;

    // Person to report the case.
    private IUser reporter;

    // Citizen's guardian.
    private IUser guardian;

    // Citizen's guardianship.
    private Guardianship guardianship;

    // Description of Guardian's authority.
    private String informationOnAuthority;

    // Description of case or problem.
    private String description;

    // Set of offers in this case.
    private Set<Pair<String, String>> offers;

    // Set of grantings in this case.
    private Set<String> grantings;

    // Set of themes
    private Set<ITheme> themes;

    // Note of special circumstances.
    private String specialCircumstances;

    // Municipality of action.
    private String municipality;

    // Citizens agreement on case opening.
    private boolean citizenConsentsCaseOpening;

    // Time and date of case opening.
    private Date dateOfOpening;

    private char totalLevelOfFunction;

    public Case(IInquiry inquiry){
        setState(ElucidationState.CASE);
        this.description = inquiry.getDescription();
        offers = new HashSet<>();
        grantings = new HashSet<>();
        themes = new HashSet<>();
        dateOfOpening = new Date();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addDescription(String description) {
        this.description = description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addReporter(IUser reporter) {
        if(this.citizen != this.reporter) this.reporter = reporter;
        else this.reporter = this.citizen;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IUser getReporter() {
        return this.reporter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGuardianship(IUser guardian, Guardianship guardianship, String informationOnAuthority) {
        this.guardian = guardian;
        this.guardianship = guardianship;
        this.informationOnAuthority = informationOnAuthority;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setConsentToCaseOpening(boolean citizenAgrees) {
        this.citizenConsentsCaseOpening = citizenAgrees;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addSpecialCircumstances(String specialCircumstances) {
        this.specialCircumstances = specialCircumstances;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addOffers(Pair<String, String>... offering) {
        this.offers.addAll(Arrays.asList(offering));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addGrantings(String... granting) {
        this.grantings.addAll(Arrays.asList(granting));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addThemes(ITheme... theme) {
        this.themes.addAll(Arrays.asList(theme));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<ITheme> getThemes() {
        return themes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTotalLevelOfFunction(char level) {
        if (level < 'A' || level > 'F') {
            throw new IllegalArgumentException("level must be between A and F, it was " + level);
        }

        this.totalLevelOfFunction = level;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public char getTotalLevelOfFunction() {
        return totalLevelOfFunction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean saveCase() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.toString();
    }
}
