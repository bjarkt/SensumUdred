package BLL;

import ACQ.*;
import BLL.log_agent.ChangeLog;
import DAL.IPersistent;

import java.util.Set;

public interface IBusiness {
	/**
	 * Injects a persistent layer into the business layer.
	 * @param persistent any persistent
	 */
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
	 * Set an event listener for when the security system 'throws' out an exception.
	 * This exception will only occur if the user does not the required security level.
	 * @param eventListener any event listener
	 */
	void setSecurityEventListener(IEventListener<SecurityException> eventListener);

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
