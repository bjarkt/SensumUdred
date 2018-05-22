package BLL.security_system;

import ACQ.IEventListener;
import ACQ.IUser;

import java.util.Set;

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

	/**
	 * Add an event listener to the set.
	 * @param eventListener any event listener
	 */
	void setEventListener(IEventListener<SecurityException> eventListener);

	/**
	 * Get the event listener.
	 * @return the event listener
	 */
	IEventListener<SecurityException> getEventListener();
}
