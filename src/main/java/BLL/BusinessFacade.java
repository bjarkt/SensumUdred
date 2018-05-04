package BLL;

import BLL.ACQ.IUser;
import BLL.account_system.ILoginService;
import BLL.account_system.LoginService;
import DAL.IPersistent;

public class BusinessFacade implements IBusiness {
	private IPersistent persistent;

	@Override
	public IUser login(String username, String password) {
		ILoginService loginService = new LoginService();
		return loginService.login(username, password);
	}

	@Override
	public void injectPersistent(IPersistent persistent) {
		this.persistent = persistent;
	}
}