package BLL.mediators;

import ACQ.*;
import BLL.Elucidation;
import BLL.Inquiry.Inquiry;
import BLL.account_system.Account;
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
     * Convert a IAccount from DAL to a IAccount from BLL.
     * The reason is the logic beneath the BLL.
     * The DAL only contains data.
     * @param dataAccount any DAL account
     * @return a BLL IAccount
     */
    static IAccount convertDataAccountToRealAccount(IAccount dataAccount) {
        Account realAccount = new Account(dataAccount.getUsername(), dataAccount.getSecurityLevel());
        realAccount.setLocked(dataAccount.isLocked());

        return realAccount;
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
        IUser creator = null;
        if (dataElucidation.getCaseworkers().stream().findFirst().isPresent()) {
            creator = MediatorHelper.convertDataUserToRealUser(dataElucidation.getCaseworkers().stream().findFirst().get(), getAddress);
        }

        IDialog dialog = null;
        if(dataElucidation.getDialog() != null) {
            dialog = new Dialog(httpClient, eboks, dataElucidation.getDialog());
        }

        Elucidation elucidation = new Elucidation(dataElucidation.getId(), dataElucidation.getCitizen(),
                creator, dialog, dataElucidation.getTask());
        elucidation.setDateOfOpening(dataElucidation.getCreationDate());

        return elucidation;
    }
}