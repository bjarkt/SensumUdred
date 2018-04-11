package UI.primary_view;

import BLL.IBusiness;
import UI.IUserInterface;
import UI.JavaFX;
import javafx.application.Application;

public class UserFacade implements IUserInterface {
	private static IBusiness business;

	@Override
	public void injectBusiness(IBusiness business) {
		UserFacade.business = business;
	}

	@Override
	public void startApplication(String[] args) {
		Application.launch(JavaFX.class, args);
	}
}