package BLL;

import ACQ.*;
import BLL.getter.address_getter.IGetAddress;
import BLL.log_agent.ChangeLog;
import BLL.open_case.ICase;
import BLL.theme_manager.IThemeManager;
import BLL.getter.user_getter.IGetUser;
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
	 * Get a user from the ssn.
	 * @param ssn any ssn
	 * @return user with that ssn
	 */
	IUser getUser(String ssn);

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
	 * Get the theme manager, for adding new themes to a case
	 * @return theme manager;
	 */
	IThemeManager getThemeManager(ICase aCase);

	/**
	 * Get the user getter object, to get users from a cpr number
	 * @return GetUser object
	 */
	IGetUser getGetUser();


	/**
	 * Get the address getter object, to get address from a cpr number
	 * @return GetAddress object
	 */
	IGetAddress getGetAddress();

	/**
	 * Creates a new inquiry.
	 */
	void createInquiry();
	IElucidation getElucidation(int ID);

}
