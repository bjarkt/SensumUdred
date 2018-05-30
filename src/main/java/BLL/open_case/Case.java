package BLL.open_case;

import ACQ.*;
import BLL.Inquiry.Inquiry;
import DAL.dataobject.Granting;
import DAL.dataobject.Offer;
import DAL.dataobject.Theme;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Case extends Inquiry implements ICase {

    // Set of offers in this case.
    private Set<IOffer> offers;

    // Set of grantings in this case.
    private Set<IGranting> grantings;

    // Set of themes
    private Set<ITheme> themes;

    // Note of special circumstances.
    private String specialCircumstances;

    // Municipality of action.
    private String municipality;

    // Citizens agreement on case opening.
    private boolean citizenConsentsCaseOpening;

    private char totalLevelOfFunction;

    public Case(ICase _case) {
        super(_case);

        setState(_case.getState());
        setCitizenConsentsCaseOpening(_case.getCitizenConsent());
        setMunicipality(_case.getActingMunicipality());
        setSpecialCircumstances(_case.getSpecialCircumstances());

        if(_case.getTotalLevelOfFunction() != ' ') setTotalLevelOfFunction(_case.getTotalLevelOfFunction());

        offers = new HashSet<>();
        for(IOffer offer : _case.getOffers()) {
            offers.add(new Offer(offer.getDescription(), offer.getParagraph()));
        }

        grantings = new HashSet<>();
        for(IGranting granting : _case.getGrantings()) {
            grantings.add(new Granting(granting.getDescription()));
        }

        themes = new HashSet<>();
        for(ITheme theme : _case.getThemes()) {
            themes.add(new Theme(theme.getTheme(), theme.getDocumentation(), theme.getSubtheme(), theme.getLevelOfFunction()));
        }
    }

    public Case(IInquiry inquiry){
        super(inquiry);
        setState(ElucidationTaskState.CASE);
        offers = new HashSet<>();
        grantings = new HashSet<>();
        themes = new HashSet<>();
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
    public void addTheme(ITheme ...themes) {
        this.themes.addAll(Arrays.asList(themes));
    }

    @Override
    public Set<IOffer> getOffers() {
        return offers;
    }

    @Override
    public Set<IGranting> getGrantings() {
        return grantings;
    }

    @Override
    public Set<IAttachment> getThirdPartyInformations() {
        return null;
    }

    @Override
    public boolean getCitizenConsent() {
        return citizenConsentsCaseOpening;
    }

    @Override
    public String getSpecialCircumstances() {
        return specialCircumstances;
    }

    @Override
    public String getActingMunicipality() {
        return municipality;
    }

    /**
     * Not implemented
     * @return null
     */
    @Override
    public String getGuardianAuthority() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public char getTotalLevelOfFunction() {
        return totalLevelOfFunction;
    }

    public void setTotalLevelOfFunction(char level) {
        if (level < 'A' || level > 'E') {
            throw new IllegalArgumentException("level must be between A and E, it was " + level);
        }

        this.totalLevelOfFunction = level;
    }


    public void addGranting(IGranting ...grantings) {
        this.grantings.addAll(Arrays.asList(grantings));
    }

    public void addOffers(IOffer ...offers) {
        this.offers.addAll(Arrays.asList(offers));
    }

    public void setSpecialCircumstances(String specialCircumstances) {
        this.specialCircumstances = specialCircumstances;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public void setCitizenConsentsCaseOpening(boolean citizenConsentsCaseOpening) {
        this.citizenConsentsCaseOpening = citizenConsentsCaseOpening;
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
