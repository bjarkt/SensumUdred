package BLL.security_system;

import ACQ.IEventListener;
import ACQ.IUser;

import java.util.HashSet;
import java.util.Set;

/**
 * Allows to set the user for the automatically security to function properly.
 * **SINGLETON**
 */
public final class SecuritySystem implements ISecurityService {
	private static ISecurityService INSTANCE;
	private IUser user;
	private IEventListener<SecurityException> event;

	private SecuritySystem() {
		event = Throwable::printStackTrace;
	}

	/**
	 * Returns the service interface for the security system.
	 * @return an {@link ISecurityService}
	 */
	public static ISecurityService getInstance() {
		if(INSTANCE == null) INSTANCE = new SecuritySystem();

		return INSTANCE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IUser getUser() {
		return user;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setUser(IUser user) {
		this.user = user;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setEventListener(IEventListener<SecurityException> eventListener) {
		event = eventListener;
	}

	@Override
	public IEventListener<SecurityException> getEventListener() {
		return event;
	}
}