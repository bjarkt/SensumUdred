package BLL;

import BLL.ACQ.HttpAcceptType;
import BLL.ACQ.HttpMethod;
import BLL.ACQ.IUser;
import BLL.account_system.ILoginService;
import BLL.account_system.LoginService;
import BLL.case_opening.ICaseOpeningService;
import BLL.case_opening.IHttp;
import BLL.log_agent.ChangeLog;
import BLL.log_system.LogAspect;
import BLL.security_system.SecurityLevel;
import DAL.IPersistent;

import java.io.IOException;
import java.util.Map;
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
	@SecurityLevel(100)
	@Override
	public Set<ChangeLog> getChangeLog() {
		return null;
	}

	@Override
	public void injectPersistent(IPersistent persistent) {
		this.persistent = persistent;
		LogAspect.setPersistent(persistent);
	}
}