package BLL;

import BLL.ACQ.IUser;
import DAL.IPersistent;

public interface IBusiness {
	void injectPersistent(IPersistent persistent);
	IUser login(String username, String password);
}
