package BLL;

import ACQ.IElucidation;
import ACQ.IUser;
import BLL.account_system.UserManager;
import BLL.log_agent.ChangeLog;
import BLL.log_system.LogAspect;
import BLL.security_system.SecurityLevel;
import DAL.IPersistent;

import java.util.Set;

public class BusinessFacade implements IBusiness {
	private IPersistent persistent;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IUser login(String username, String password) {
		return UserManager.getInstance().signIn(username, password);
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
		LogAspect.setPersistent(persistent);
	}
}