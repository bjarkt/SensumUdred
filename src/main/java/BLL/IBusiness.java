package BLL;

import DAL.IPersistent;

public interface IBusiness {
	void injectPersistent(IPersistent persistent);
	boolean login(String username, String password);
}
