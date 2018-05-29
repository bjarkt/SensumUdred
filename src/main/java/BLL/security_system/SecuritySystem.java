package BLL.security_system;

import ACQ.IAccount;
import ACQ.IEventListener;
import ACQ.ISecurityService;

/**
 * Allows to set the user for the automatically security to function properly.
 * **SINGLETON**
 */
public final class SecuritySystem implements ISecurityService {
	private static ISecurityService INSTANCE;
	private IAccount account;
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
	public IAccount getAccount() {
		return account;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setAccount(IAccount account) {
		this.account = account;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setEventListener(IEventListener<SecurityException> eventListener) {
		event = eventListener;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IEventListener<SecurityException> getEventListener() {
		return event;
	}
}