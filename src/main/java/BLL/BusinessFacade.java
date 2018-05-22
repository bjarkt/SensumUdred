package BLL;

import ACQ.IElucidation;
import ACQ.ISigningService;
import ACQ.IUser;
import ACQ.IUserManager;
import BLL.account_system.UserManager;
import BLL.log_agent.ChangeLog;
import BLL.log_system.LogAspect;
import BLL.security_system.SecurityLevel;
import DAL.IPersistent;

import java.util.Set;

public class BusinessFacade implements IBusiness {
	private IPersistent persistent;
	private IUserManager userManager;

	public BusinessFacade() {

	}

	@Override
	public ISigningService getSigningService() {
		return (ISigningService) userManager;
	}

	@Override
	public IUserManager getUserManager() {
		return userManager;
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
	public Set<IUser> getAllUsers() {
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

	@Override
	public void injectPersistent(IPersistent persistent) {
		this.persistent = persistent;
		this.userManager = new UserManager(persistent.getDatabaseService());
		LogAspect.setPersistent(persistent);
	}
}