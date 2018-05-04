package BLL;

import BLL.ACQ.HttpAcceptType;
import BLL.ACQ.HttpMethod;
import BLL.ACQ.IUser;
import BLL.account_system.ILoginService;
import BLL.account_system.LoginService;
import BLL.case_opening.ICaseOpeningService;
import BLL.case_opening.IHttp;
import DAL.IPersistent;

import java.io.IOException;
import java.util.Map;

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