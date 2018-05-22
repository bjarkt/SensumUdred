package BLL.security_system;

import ACQ.IAccount;
import ACQ.IEventListener;
import ACQ.IUser;

import java.util.Set;

public interface ISecurityService {
	/**
	 * Return the account in the Security System.
	 */
	IAccount getAccount();

	/**
	 * Set the user inside the security system.
	 * The security system is completely automatic.
	 * @param user the current user using the system
	 */
	void setAccount(IAccount account);

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
