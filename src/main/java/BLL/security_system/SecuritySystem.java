package BLL.security_system;

import BLL.ACQ.IUser;

/**
 * It gives access to the {@link ISecurityService#hasAccess(int)} method,
 * which can be used when a user has been given to the Security System.
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
	public void setUser(IUser user) {
		this.user = user;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasAccess(int securityLevel) {
		return user.getEntryLevel() >= securityLevel;
	}
}