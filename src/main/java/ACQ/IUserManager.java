package ACQ;

public interface IUserManager extends ISigningService {
	/**
	 * Returns the signed in account.
	 * @return if no account is signed in, null; otherwise the account
	 */
	IAccount getSignedInAccount();

	/**
	 * Returns the signed in user.
	 * @return if no user is signed in, null; otherwise the user
	 */
	IUser getSignedInUser();
}
