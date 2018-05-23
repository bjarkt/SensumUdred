package ACQ;

import BLL.Inquiry.Inquiry;
import BLL.meeting.IDialog;
import BLL.open_case.Case;
import BLL.open_case.Guardianship;
import BLL.open_case.ICase;
import javafx.util.Pair;

import java.util.*;

public class Elucidation implements IElucidation {

    private IUser citizen;
    private Set<IUser> caseworkers;
    private Task task;
    private Date dateOfOpening;
    private IDialog dialog;


    public Elucidation(IUser citizen, IUser creator, IDialog dialog){
        dateOfOpening = new Date();
        this.citizen = citizen;
        caseworkers = new HashSet<>();
        caseworkers.add(creator);
        task = new Inquiry();
        this.dialog = dialog;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IUser getCitizen() {
        return citizen;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCaseworker(IUser... caseworker) {
        caseworkers.addAll(Arrays.asList(caseworker));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<IUser> getCaseworkers() {
        return caseworkers;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void openCase(String description, List<Pair<String, String>> offerings, List<String> grantings, IUser guardian, Guardianship guardianship, String informationAboutAuthority, boolean citizenAgreesWithOpeningOfCase, String citizensMunicipality, String specialCircumstances) {
        task = new Case((IInquiry) task);
        ((ICase) task).addDescription(description);
        offerings.forEach(offering -> ((ICase) task).addOffers(offering));
        grantings.forEach(granting -> ((ICase) task).addGrantings(granting));
        ((ICase) task).setGuardianship(guardian, guardianship, informationAboutAuthority);
        ((ICase) task).setConsentToCaseOpening(citizenAgreesWithOpeningOfCase);
        ((ICase) task).setMunicipality(citizensMunicipality);
        ((ICase) task).addSpecialCircumstances(specialCircumstances);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date getTimeOfOpening() {
        return dateOfOpening;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IDialog getDialog() {
        return dialog;
    }
}
