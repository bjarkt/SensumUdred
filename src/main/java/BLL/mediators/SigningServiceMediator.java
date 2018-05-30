package BLL.mediators;

import ACQ.*;
import BLL.getter.address_getter.IGetAddress;

public class SigningServiceMediator implements ISigningService {
    /**
     * IElucidationService from the DAL
     */
    private ISigningService dataSigningService;

    /**
     * To get Address from CPR number
     */
    private IGetAddress getAddress;

    public SigningServiceMediator(ISigningService dataSigningService, IGetAddress getAddress) {
        this.dataSigningService = dataSigningService;
        this.getAddress = getAddress;
    }

    /**
     * {@inheritDoc}
     */
    @Loggable(action = LogAction.CALL, actionDescription = "Attempted login", level = LogLevel.INFO)
    @Override
    public IProfile signIn(String username, String password) {
        IProfile dataProfile = dataSigningService.signIn(username, password);
        IUser realUser = MediatorHelper.convertDataUserToRealUser(dataProfile.getUser(), getAddress);

        return new IProfile() {
            @Override
            public IUser getUser() {
                return realUser;
            }

            @Override
            public IAccount getAccount() {
                return dataProfile.getAccount();
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean signOut(String accountName) {
        return dataSigningService.signOut(accountName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean signUpUser(String ssn) {
        return dataSigningService.signUpUser(ssn);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean signUpUser(String ssn, String firstName, String lastName, String phoneNumber, String email) {
        return dataSigningService.signUpUser(ssn ,firstName, lastName, phoneNumber, email);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean signUpUser(IUser user) {
        return dataSigningService.signUpUser(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean signUpAccount(String ssn, String username, String password, int securityLevel) {
        return dataSigningService.signUpAccount(ssn, username, password, securityLevel);
    }
}
