package BLL.mediators;

import ACQ.IAccount;
import ACQ.IDefaultService;
import ACQ.IUser;
import BLL.getter.address_getter.IGetAddress;

import java.util.HashSet;
import java.util.Set;

public class DefaultServiceMediator implements IDefaultService {

    /**
     * IElucidationService from the DAL
     */
    private IDefaultService dataDefaultService;

    /**
     * To get Address from CPR number
     */
    private IGetAddress getAddress;

    public DefaultServiceMediator(IDefaultService dataDefaultService, IGetAddress getAddress) {
        this.dataDefaultService = dataDefaultService;
        this.getAddress = getAddress;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IUser getUser(String ssn) {
        IUser dataUser = dataDefaultService.getUser(ssn);
        IUser realUser = MediatorHelper.convertDataUserToRealUser(dataUser, getAddress);

        return realUser;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean accountExists(String accountName) {
        return dataDefaultService.accountExists(accountName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean userExists(String ssn) {
        return dataDefaultService.userExists(ssn);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<IUser> getAllUsers(int limit) {
        Set<IUser> dataUsers = dataDefaultService.getAllUsers(limit);
        Set<IUser> realUsers = new HashSet<>();

        for (IUser dataUser : dataUsers) {
            IUser realUser = MediatorHelper.convertDataUserToRealUser(dataUser, getAddress);
            realUsers.add(realUser);
        }

        return realUsers;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<IAccount> getAllAccounts(int limit) {
        return dataDefaultService.getAllAccounts(limit);
    }
}
