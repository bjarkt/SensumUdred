package BLL;

import ACQ.*;
import BLL.account_system.Address;
import BLL.account_system.UserManager;
import BLL.address_getter.GetAddress;
import BLL.address_getter.IGetAddress;
import BLL.log_agent.ChangeLog;
import BLL.log_system.LogAspect;
import BLL.open_case.ICase;
import BLL.security_system.SecurityLevel;
import BLL.theme_manager.IThemeManager;
import BLL.theme_manager.ThemeManager;
import BLL.user_getter.GetUser;
import BLL.user_getter.IGetUser;
import BLL.security_system.SecuritySystem;
import DAL.IPersistent;

import java.util.Set;

public class BusinessFacade implements IBusiness {
	private IPersistent persistent;
	private IUserManager userManager;

	public BusinessFacade() {

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ISigningService getSigningService() {
		return (ISigningService) userManager;
	}

	@Override
	public IElucidation getElucidation(int ID) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@SecurityLevel(1000)
	@Override
	public IUserManager getUserManager() {
		return userManager;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setSecurityEventListener(IEventListener<SecurityException> eventListener) {
		SecuritySystem.getInstance().setEventListener(eventListener);
	}

	/**
	 * {@inheritDoc}
	 */
	@SecurityLevel(1000)
	@Override
	public Set<ChangeLog> getChangeLog() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@SecurityLevel(0)
	@Override
	public Set<IElucidation> getMyElucidations() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */

	@SecurityLevel(500)
	@Override
	public void createInquiry() {

	}

	/**
	 * {@inheritDoc}
	 */
	@SecurityLevel(500)
	@Override
	public Set<IUser> searchUsersContaining(String query) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@SecurityLevel(500)
	@Override
	public void addCaseworkerToCase(IUser user) {

	}

	/**
	 * {@inheritDoc}
	 */
	@SecurityLevel(500)
	@Override
	public IThemeManager getThemeManager(ICase aCase) {
		return new ThemeManager(aCase);
	}

	/**
	 * {@inheritDoc}
	 */
	@SecurityLevel(500)
	@Override
	public IGetUser getGetUser() {
		return new GetUser(persistent.getHttp());
	}

	/**
	 * {@inheritDoc}
	 */
	@SecurityLevel(500)
	@Override
	public IGetAddress getGetAddress() {
		return new GetAddress(persistent.getHttp(), Address.class);
	}

	@Override
	public void injectPersistent(IPersistent persistent) {
		this.persistent = persistent;
		this.userManager = new UserManager(persistent.getDatabaseService());
		LogAspect.setPersistent(persistent);
	}
}