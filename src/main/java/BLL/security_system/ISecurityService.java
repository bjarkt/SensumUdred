package BLL.security_system;

import BLL.ACQ.IUser;

public interface ISecurityService {
	/**
	 * Returns the user in the Security System.
	 */
	IUser getUser();

	/**
	 * Sets the user inside the security system.
	 * The security system is completely automatic.
	 * @param user the current user using the system
	 */
	void setUser(IUser user);
}
