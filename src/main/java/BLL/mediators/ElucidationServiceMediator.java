package BLL.mediators;

import ACQ.*;
import BLL.Elucidation;
import BLL.Inquiry.Inquiry;
import BLL.account_system.Address;
import BLL.account_system.User;
import BLL.getter.address_getter.IGetAddress;
import BLL.meeting.Dialog;
import BLL.meeting.Meeting;

import java.util.*;

public class ElucidationServiceMediator implements IElucidationService {

    /**
     * IElucidationService from the DAL
     */
    private IElucidationService dataElucidationService;

    /**
     * HTTP Client used in dialog, which is used in elucidation;
     */
    private IHttp httpClient;

    /**
     * HTTP Client used in dialog, which is used in elucidation;
     */
    private IEBoks eboks;

    /**
     * To get Address from CPR number
     */
    private IGetAddress getAddress;

    public ElucidationServiceMediator(IElucidationService dataElucidationService, IHttp httpClient, IEBoks eboks, IGetAddress getAddress) {
        this.dataElucidationService = dataElucidationService;
        this.httpClient = httpClient;
        this.eboks = eboks;
        this.getAddress = getAddress;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IElucidation createElucidation(IUser citizen, Set<IUser> caseworkers, IInquiry inquiry) {
        IElucidation DALElucidation = dataElucidationService.createElucidation(citizen, caseworkers, inquiry);

        IElucidation realElucidation = this.convertToRealElucidation(DALElucidation);

        return realElucidation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateInquiry(long id, IInquiry inquiry) {
        return dataElucidationService.updateInquiry(id, inquiry);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateState(long id, boolean isclosed) {
        return dataElucidationService.updateState(id, isclosed);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateCaseworkers(long id, Set<IUser> users) {
        return dataElucidationService.updateCaseworkers(id, users);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateTaskState(long id, ElucidationTaskState state) {
        return dataElucidationService.updateTaskState(id, state);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateCitizenConsent(long id, boolean hasConsent) {
        return dataElucidationService.updateCitizenConsent(id, hasConsent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateActingMunicipality(long id, String municipality) {
        return dataElucidationService.updateActingMunicipality(id, municipality);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateSpecialCircumstances(long id, String newDescription) {
        return dataElucidationService.updateSpecialCircumstances(id, newDescription);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateGuardianAuthority(long id, String newDescription) {
        return dataElucidationService.updateGuardianAuthority(id, newDescription);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateTotalLevelOfFunction(long id, char letter) {
        return dataElucidationService.updateTotalLevelOfFunction(id, letter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateOffers(long id, Set<IGranting> offers) {
        return dataElucidationService.updateOffers(id, offers);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateGrantings(long id, Set<IOffer> grantings) {
        return dataElucidationService.updateGrantings(id, grantings);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateThemes(long id, Set<ITheme> themes) {
        return dataElucidationService.updateThemes(id, themes);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateMeeting(long id, IMeeting meeting) {
        return dataElucidationService.updateMeeting(id, meeting);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateThirdPartyAttachments(long id, Set<IAttachment> attachments) {
        return dataElucidationService.updateThirdPartyAttachments(id, attachments);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IElucidation getElucidation(long id) {
        IElucidation DALElucidation = dataElucidationService.getElucidation(id);

        IElucidation realElucidation = this.convertToRealElucidation(DALElucidation);

        return realElucidation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<IElucidation> getOpenElucidationsFromSSN(String ssn) {
        Set<IElucidation> realOpenElucidations = new TreeSet<>(getIElucidationComparator());
        dataElucidationService.getOpenElucidationsFromSSN(ssn).forEach(e -> realOpenElucidations.add(convertToRealElucidation(e)));
        return realOpenElucidations;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<IElucidation> getClosedElucidationsFromSSN(String ssn) {
        Set<IElucidation> realClosedElucidations = new TreeSet<>(getIElucidationComparator());
        dataElucidationService.getClosedElucidationsFromSSN(ssn).forEach(e -> realClosedElucidations.add(convertToRealElucidation(e)));
        return realClosedElucidations;
    }

    /**
     * Convert a elucidation from the DAL to BLL elucidation.
     * The reason to change is for the logic beneath the BLL object.
     * DAL object is only for getting the data.
     * @param DALElucidation any dal elucidation
     * @return a BLL elucidation
     */
    private Elucidation convertToRealElucidation(IElucidation DALElucidation) {
        Set<IMeeting> realMeetings = new HashSet<>();

        if (DALElucidation.getDialog() != null) {
            for (IMeeting dalMeeting : DALElucidation.getDialog().getMeetings()) {
                BLL.meeting.Meeting bllMeeting = new Meeting(dalMeeting.getCreator(), eboks, dalMeeting.getNumber());
                bllMeeting.setInformation(dalMeeting.getInformation());

                Calendar cal = Calendar.getInstance();
                cal.setTime(dalMeeting.getMeetingDate());

                bllMeeting.setMeetingDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE));
                bllMeeting.setNumber(dalMeeting.getNumber());

                realMeetings.add(bllMeeting);
            }
        }

        Dialog realDialog = new Dialog(httpClient, eboks);
        realDialog.setMeetings(realMeetings);

        IUser creator = null;
        if (DALElucidation.getCaseworkers().stream().findFirst().isPresent()) {
            creator = DALElucidation.getCaseworkers().stream().findFirst().get();
        }

        Set<IUser> caseworkersExceptCreator = DALElucidation.getCaseworkers();
        caseworkersExceptCreator.remove(creator);

        ITask realInquiry = new Inquiry(((IInquiry)DALElucidation.getTask()));

        IUser citizen = DALElucidation.getCitizen();
        User realCitizen = new User(citizen.getSocialSecurityNumber(), citizen.getFirstName(), citizen.getLastName());
        realCitizen.setPhoneNumber(citizen.getPhoneNumber());
        realCitizen.setEmail(citizen.getEmail());
        realCitizen.setAddress((Address) getAddress.getAddress(realCitizen.getSocialSecurityNumber()));

        Elucidation realElucidation = new Elucidation(DALElucidation.getId(), realCitizen, creator, realDialog, realInquiry);
        realElucidation.addCaseworker(caseworkersExceptCreator.toArray(new IUser[0]));

        return realElucidation;
    }

    /**
     * A comparator as a function method. It compares the ids.
     * @return a comparator for IElucidations.
     */
    private Comparator<IElucidation> getIElucidationComparator() {
        return Comparator.comparingLong(IElucidation::getId);
    }
}
