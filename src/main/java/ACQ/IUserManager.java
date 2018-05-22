package ACQ;

import BLL.account_system.User;

public interface IUserManager extends IAdminService {
	/**
	 * Returns the signed in account.
	 * Does nothing, so for now use {@link User#getAccount()}.
	 * @return if no account is signed in, null; otherwise the account
	 */
	IAccount getSignedInAccount();

	/**
	 * Returns the signed in user.
	 * @return if no user is signed in, null; otherwise the user
	 */
	IUser getSignedInUser();
}
