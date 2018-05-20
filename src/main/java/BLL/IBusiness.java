package BLL;

import BLL.ACQ.IElucidation;
import BLL.ACQ.IUser;
import BLL.log_agent.ChangeLog;
import BLL.security_system.SecurityLevel;
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
	 * Creates a new inquiry.
	 */
	void createInquiry();


}
