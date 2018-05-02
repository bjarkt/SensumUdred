package BLL.security_system;

import BLL.ACQ.IUser;

public interface ISecurityService {
	/**
	 * Sets the user inside the access system.
	 * Whenever {@link ISecurityService#hasAccess(int)} is used, it checks with the user.
	 * @param user the current user using the system
	 */
	void setUser(IUser user);

	/**
	 *
	 * Checks whether the user has access to parameter securityLevel.
	 * @param securityLevel an integer specifying the access level of the method
	 * @throws SecurityException user has not access
	 */
	void hasAccess(int securityLevel) throws SecurityException;
}
