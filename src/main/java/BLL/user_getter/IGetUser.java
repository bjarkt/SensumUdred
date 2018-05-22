package BLL.user_getter;

import ACQ.IUser;

public interface IGetUser {

    /**
     * Get user information from cpr number
     * Account is null
     * @param cpr cpr/ssn number
     * @return a user
     */
    IUser getUser(String cpr);
}
