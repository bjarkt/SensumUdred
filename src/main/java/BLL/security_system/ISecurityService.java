package BLL.security_system;

import BLL.ACQ.IUser;

public interface ISecurityService {
	/**
	 * Sets the user inside the security system.
	 * The security system is completely automatic.
	 * @param user the current user using the system
	 */
	void setUser(IUser user);
}
