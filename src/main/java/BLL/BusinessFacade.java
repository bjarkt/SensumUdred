package BLL;

import DAL.IPersistent;

public class BusinessFacade implements IBusiness {
	private IPersistent persistent;

	@Override
	public boolean login(String username, String password) {
		return true;
	}

	@Override
	public void injectPersistent(IPersistent persistent) {
		this.persistent = persistent;
	}
}