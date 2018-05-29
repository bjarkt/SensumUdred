package BLL.mediators;

import ACQ.IUser;
import BLL.account_system.Address;
import BLL.account_system.User;
import BLL.getter.address_getter.IGetAddress;

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
}