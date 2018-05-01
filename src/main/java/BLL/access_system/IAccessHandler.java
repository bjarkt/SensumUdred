package BLL.access_system;

import BLL.ACQ.IUser;

public interface IAccessHandler {
	void setUser(IUser user);
	boolean init();
}
