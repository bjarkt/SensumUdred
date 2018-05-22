package BLL;

import ACQ.IElucidation;
import ACQ.IUser;
import BLL.log_agent.ChangeLog;
import DAL.IPersistent;

import java.util.Set;

public interface IBusiness {
	void injectPersistent(IPersistent persistent);

	/**
	 * Logs user into the system.
	 * @param username	user's account's username
	 * @param password	user's account's password
	 * @return	the user associated with the account credentials.
	 */
	IUser login(String username, String password);

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
