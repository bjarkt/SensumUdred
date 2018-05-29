package BLL.mediators;

import ACQ.*;
import BLL.Elucidation;
import BLL.Inquiry.Inquiry;
import BLL.account_system.Address;
import BLL.account_system.User;
import BLL.getter.address_getter.IGetAddress;
import BLL.meeting.Dialog;
import BLL.meeting.Meeting;
import BLL.open_case.Case;
import BLL.theme_manager.Theme;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

class MediatorHelper {
    /**
     * Convert a IUser from DAL to a IUser from BLL.
     * The reason is the logic beneath the BLL.
     * The DAL only contains data.
     * @param dataUser any DAL user
     * @param getAddress a getAdress object
     * @return a BLL IUser
     */
    static IUser convertDataUserToRealUser(IUser dataUser, IGetAddress getAddress) {
        User realUser = new User(dataUser.getSocialSecurityNumber(), dataUser.getFirstName(), dataUser.getLastName());
        realUser.setPhoneNumber(dataUser.getPhoneNumber());
        realUser.setEmail(dataUser.getEmail());
        realUser.setAddress((Address) getAddress.getAddress(realUser.getSocialSecurityNumber()));

        return realUser;
    }

    /**
     *
     /**
     * Convert a elucidation from the DAL to BLL elucidation.
     * The reason to change is for the logic beneath the BLL object.
     * DAL object is only for getting the data.
     * @param dataElucidation any dal elucidation
     * @param getAddress address getter object
     * @param httpClient http
     * @param eboks eboks
     * @return a BLL elucidation
     */
    static IElucidation convertDataElucidationToRealElucidation(IElucidation dataElucidation, IGetAddress getAddress, IHttp httpClient, IEBoks eboks) {
        boolean isCase = dataElucidation.getTask().getState() == ElucidationTaskState.CASE;

        // Setup meetings
        Set<IMeeting> realMeetings = new HashSet<>();
        if (dataElucidation.getDialog() != null) {
            for (IMeeting dalMeeting : dataElucidation.getDialog().getMeetings()) {
                Meeting bllMeeting = new Meeting(dalMeeting.getCreator(), eboks, dalMeeting.getNumber());
                bllMeeting.setInformation(dalMeeting.getInformation());

                Calendar cal = Calendar.getInstance();
                cal.setTime(dalMeeting.getMeetingDate());

                bllMeeting.setMeetingDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE));
                bllMeeting.setNumber(dalMeeting.getNumber());

                realMeetings.add(bllMeeting);
            }
        }

        // Setup dialog
        Dialog realDialog = new Dialog(httpClient, eboks);
        realDialog.setMeetings(realMeetings);

        // Setup creator and caseworkers
        IUser creator = null;
        if (dataElucidation.getCaseworkers().stream().findFirst().isPresent()) {
            creator = MediatorHelper.convertDataUserToRealUser(dataElucidation.getCaseworkers().stream().findFirst().get(), getAddress);
        }
        Set<IUser> caseworkersExceptCreator = dataElucidation.getCaseworkers();

        caseworkersExceptCreator.remove(dataElucidation.getCaseworkers().stream().findFirst().get());

        // Setup inquiry / case
        switch (dataElucidation.getTask().getState()) {
            case INQUIRY:
                break;
            case CASE:
                break;
        }
        ITask realInquiry = new Inquiry(((IInquiry)dataElucidation.getTask()));

        // Setup citizen
        IUser realCitizen = MediatorHelper.convertDataUserToRealUser(dataElucidation.getCitizen(), getAddress);

        // Setup themes, grantings, offers
        Set<ITheme> realThemes = new HashSet<>();
        Set<IGranting> realGrantings = new HashSet<>();
        Set<IOffer> realOfferings = new HashSet<>();
        Case realCase = null;
        if (isCase) {
            ICase dataCase = ((ICase)dataElucidation.getTask());

            for (ITheme dataTheme : dataCase.getThemes()) {
                ITheme realTheme = new Theme(dataTheme.getTheme(), dataTheme.getSubtheme(), dataTheme.getDocumentation());
                realTheme.setLevelOfFunction(dataTheme.getLevelOfFunction());
                realThemes.add(realTheme);
            }

            realGrantings.addAll(dataCase.getGrantings());

            realOfferings.addAll(dataCase.getOffers());

            realCase = new Case(new Inquiry(((ICase) dataElucidation.getTask()).getDescription(), ((ICase) dataElucidation.getTask()).getSource()));
            realThemes.forEach(realCase::addTheme);
            realGrantings.forEach(realCase::addGranting);
            realOfferings.forEach(realCase::addOffers);

            realCase.setCitizenConsentsCaseOpening(dataCase.getCitizenConsent());
            realCase.setMunicipality(dataCase.getActingMunicipality());
            realCase.setSpecialCircumstances(dataCase.getSpecialCircumstances());
            if (dataCase.getTotalLevelOfFunction() != ' ') {
                realCase.setTotalLevelOfFunction(dataCase.getTotalLevelOfFunction());
            }
        }

        // Create real elucidation
        Elucidation realElucidation = null;
        if (isCase) {
            realElucidation = new Elucidation(dataElucidation.getId(), realCitizen, creator, realDialog, realCase);
        } else {
            realElucidation = new Elucidation(dataElucidation.getId(), realCitizen, creator, realDialog, realInquiry);
        }
        realElucidation.addCaseworker(caseworkersExceptCreator.toArray(new IUser[0]));
        realElucidation.setDateOfOpening(dataElucidation.getCreationDate());

        return realElucidation;
    }
}