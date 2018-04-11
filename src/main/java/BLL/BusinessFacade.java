package BLL;

import DAL.IPersistent;

public class BusinessFacade implements IBusiness {
	private IPersistent persistent;

	@Override
	public void injectPersistent(IPersistent persistent) {
		this.persistent = persistent;
	}
}