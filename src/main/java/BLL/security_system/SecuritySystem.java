package BLL.security_system;

import ACQ.IUser;

/**
 * Allows to set the user for the automatically security to function properly.
 * **SINGLETON**
 */
public final class SecuritySystem implements ISecurityService {
	private static ISecurityService INSTANCE;
	private IUser user;

	private SecuritySystem() {}

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
		SecurityAspect.setUserLevel(user);
	}
}