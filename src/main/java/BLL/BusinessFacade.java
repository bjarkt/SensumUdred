package BLL;

<<<<<<< HEAD
import BLL.ACQ.HttpAcceptType;
import BLL.ACQ.HttpMethod;
import BLL.ACQ.IElucidation;
=======
>>>>>>> 6ada9c4a53d8a7dd97697a8b271e78d2d4a8900a
import BLL.ACQ.IUser;
import BLL.account_system.ILoginService;
import BLL.account_system.LoginService;
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
		ILoginService loginService = new LoginService();
		return loginService.login(username, password);
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

	@Override
	public void injectPersistent(IPersistent persistent) {
		this.persistent = persistent;
		LogAspect.setPersistent(persistent);
	}
}