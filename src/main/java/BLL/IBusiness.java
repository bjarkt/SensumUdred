package BLL;

import ACQ.IElucidation;
import ACQ.ISigningService;
import ACQ.IUser;
import ACQ.IUserManager;
import BLL.log_agent.ChangeLog;
import DAL.IPersistent;

import java.util.Set;

public interface IBusiness {
	void injectPersistent(IPersistent persistent);

	/**
	 * Get the Signing Service.
	 * It contains sign in, sign out and sign up.
	 * @return signing service
	 */
	ISigningService getSigningService();

	/**
	 * Get the User Manager.
	 * It can receive the signed user (if any) and contains admin services,
	 * such as changePassword, lock and unlock accounts.
	 * @return user manager
	 */
	IUserManager getUserManager();

	/**
	 * Get the complete change log.
	 * @return	change logl
	 */
	Set<ChangeLog> getChangeLog();

	/**
	 * Get the user's elucidations.
	 * @return user's elucidations.
	 */
	Set<IElucidation> getMyElucidations();

	/**
	 * Get all users in the system.
	 * @return users in the system.
	 */
	Set<IUser> getAllUsers();

	/**
	 * Search for users. Returns set with matched users.
	 * @return	set of matched users.
	 */
	Set<IUser> searchUsersContaining(String query);

	/**
	 * Assigns caseworker to case.
	 */
	void addCaseworkerToCase(IUser user);

	/**
	 * Creates a new inquiry.
	 */
	void createInquiry();


}
