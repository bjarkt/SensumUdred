package UI;

import BLL.IBusiness;

public interface IUserInterface {
	/**
	 * Injects reference to the business facade.
	 * @param business
	 */
	void injectBusiness(IBusiness business);

	/**
	 * Starts the application.
	 * @param args	arguments.
	 */
	void startApplication(String[] args);

	/**
	 * Handles everything necessary when app closes.
	 */
	void shutdown();
}
