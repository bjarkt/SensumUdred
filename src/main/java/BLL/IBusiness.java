package BLL;

import BLL.ACQ.IUser;
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
	 * @return
	 */
	Set<ChangeLog> getChangeLog();



}
