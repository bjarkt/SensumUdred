package UI.primary_view;
import BLL.IBusiness;
import UI.IUserInterface;
import UI.JavaFX;
import UI.components.ComponentLoader;
import UI.components.drawer.DrawerController;
import UI.components.drawer.IDrawerRequire;
import UI.components.header.HeaderController;
import UI.components.landing_page.LandingPageController;
import UI.components.log_in_page.LogInViewController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import sun.plugin.javascript.navig.Anchor;

import java.net.URL;
import java.util.ResourceBundle;

public class UserFacade implements IUserInterface, Initializable {
	private static IBusiness business;

	private HeaderController headerController;
	private LandingPageController landingPage;
	private LogInViewController logInView;
	private DrawerController drawer;


	@FXML
	private AnchorPane screen;

	@FXML
	private BorderPane canvas;


	public UserFacade(){
		headerController = new HeaderController();
		landingPage = new LandingPageController();
		logInView = new LogInViewController();
		drawer = new DrawerController();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		canvas.setTop(headerController.getView());
		canvas.setCenter(landingPage.getView());

		drawer.setRequired(new IDrawerRequire() {
			@Override
			public AnchorPane getParent() {
				return screen;
			}
		});


		headerController.onMenuClick(data -> {
			drawer.toggleVisibility();
		});

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