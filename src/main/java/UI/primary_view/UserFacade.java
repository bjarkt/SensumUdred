package UI.primary_view;
import BLL.IBusiness;
import UI.IUserInterface;
import UI.JavaFX;
import UI.components.ComponentLoader;
import UI.components.header.HeaderController;
import UI.components.landing_page.LandingPageController;
import UI.components.log_in_page.LogInViewController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import java.net.URL;
import java.util.ResourceBundle;

public class UserFacade implements IUserInterface, Initializable {
	private static IBusiness business;

	private HeaderController headerController;
	private LandingPageController landingPage;
	private LogInViewController logInView;

	@FXML
	private BorderPane canvas;

	public UserFacade(){
		headerController = new HeaderController();
		landingPage = new LandingPageController();
		logInView = new LogInViewController();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		canvas.setTop(headerController.getView());
		canvas.setCenter(landingPage.getView());

		landingPage.onLogIn(data -> {
			ComponentLoader.removeComponent(landingPage);
			canvas.setCenter(logInView.getView());
		});

		logInView.onLogIn(data -> {
			if( business.login(data[0], data[1]) != null){
				System.out.println("Access granted for: ");
				System.out.println(business.login(data[0], data[1]));
			} else{
				logInView.writeError("Brugernavn eller password er forkert.");
			}
		});

	}

	@Override
	public void injectBusiness(IBusiness business) { UserFacade.business = business; }

	@Override
	public void startApplication(String[] args) {
		Application.launch(JavaFX.class, args);
	}
}