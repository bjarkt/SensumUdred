package UI;

import BLL.IBusiness;

public interface IUserInterface {
	void injectBusiness(IBusiness business);
	void startApplication(String[] args);
}
