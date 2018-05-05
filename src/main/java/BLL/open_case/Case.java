package BLL.open_case;

import BLL.ACQ.IUser;
import BLL.ACQ.Task;
import BLL.Inquiry.IInquiry;
import BLL.Inquiry.Inquiry;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Case extends Task implements ICase {

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
    private Set<IOffer> offers;

    // Set of grantings in this case.
    private Set<IGranting> grantings;

    // Note of special circumstances.
    private String specialCircumstances;

    // Municipality of action.
    private String municipality;

    // Citizens agreement on case opening.
    private boolean citizenConsentsCaseOpening;

    // Time and date of case opening.
    private Date dateOfOpening;

    public Case(IInquiry inquiry){
        this.description = inquiry.getDescription();
        offers = new HashSet<>();
        grantings = new HashSet<>();
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
    public void addOffer(IOffer offer) {
        this.offers.add(offer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<IOffer> getOffers() {
        return this.offers;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addGranting(IGranting granting) {
        this.grantings.add(granting);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<IGranting> getGrantings() {
        return this.grantings;
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
    public boolean saveCase() {
        return false;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.toString();
    }
}
