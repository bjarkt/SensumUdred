package BLL.security_system;

import BLL.ACQ.IUser;

public final class SecuritySystem implements ISecurityService {
	private static ISecurityService INSTANCE;
	private IUser user;

	private SecuritySystem() {}

	public static ISecurityService getINSTANCE() {
		if(INSTANCE == null) INSTANCE = new SecuritySystem();

		return INSTANCE;
	}

	@Override
	public void setUser(IUser user) {
		this.user = user;
	}

	@Override
	public boolean hasAccess(int securityLevel) {
		return user.getEntryLevel() >= securityLevel;
	}
}