package ACQ;

public interface IUserManager extends IAdminService {
	/**
	 * Returns the signed in user.
	 * @return user, otherwise null, if no user is signed in
	 */
	boolean getSignedInUser();
}
