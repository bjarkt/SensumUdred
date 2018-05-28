package BLL.mediators;

import ACQ.IUser;
import BLL.account_system.Address;
import BLL.account_system.User;
import BLL.getter.address_getter.IGetAddress;

class MediatorHelper {

    static IUser convertDataUserToRealUser(IUser dataUser, IGetAddress getAddress) {
        User realUser = new User(dataUser.getSocialSecurityNumber(), dataUser.getFirstName(), dataUser.getLastName());
        realUser.setPhoneNumber(dataUser.getPhoneNumber());
        realUser.setEmail(dataUser.getEmail());
        realUser.setAddress((Address) getAddress.getAddress(realUser.getSocialSecurityNumber()));

        return realUser;
    }
}
