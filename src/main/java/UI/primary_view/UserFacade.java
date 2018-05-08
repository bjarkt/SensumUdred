package UI.primary_view;
import BLL.IBusiness;
import UI.IUserInterface;
import UI.JavaFX;
import UI.components.Component;
import UI.components.IComponent;
import UI.components.drawer.DrawerController;
import UI.components.drawer.IDrawer;
import UI.components.drawer.IDrawerRequire;
import UI.components.header.HeaderController;
import UI.components.header.IHeader;
import UI.components.home_view.HomeViewController;
import UI.components.home_view.IHomeView;
import UI.components.log_in_page.ILogInView;
import UI.components.log_in_page.LogInViewController;
import UI.components.popUp.IPopUpRequire;
import UI.components.popUp.IPopup;
import UI.components.popUp.PopUpController;
import com.jfoenix.controls.JFXDialog;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class UserFacade implements IUserInterface, Initializable {
	private static IBusiness business;

	private IHeader headerController;
	private ILogInView logInView;
	private IDrawer drawer;
	private IHomeView homeView;
	private IPopup popUp;

	/** Reference to the current whole page component visible. */
	private IComponent currentCenterView;

	@FXML
	private AnchorPane screen;

	@FXML
	private BorderPane canvas;


	/**
	 * Constructor for UserFacade. Instantiates the needed components.
	 */
	public UserFacade(){
		headerController = new HeaderController();
		logInView = new LogInViewController();
		homeView = new HomeViewController();

		drawer = new DrawerController(new IDrawerRequire() {
			@Override
			public AnchorPane getParent() {
				return screen;
			}
		});

		popUp = new PopUpController(new IPopUpRequire() {
			@Override
			public AnchorPane getParent() { return screen; }
		});


	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		canvas.setTop(headerController.getView());
		setCenter(logInView);

		headerController.onMenuClick(data -> {
			drawer.open();
		});

		headerController.onMessagesClick(data -> {

		});

		logInView.onLogIn(data -> {
			if( business.login(data[0], data[1]) != null){
				setCenter(homeView);
			} else{
				logInView.writeError("Brugernavn eller password er forkert.");
			}
		});

	}

	/**
	 * Loads the center of the screen with a component.
	 * @param component Component to be loaded into center view.
	 */
	private void setCenter(IComponent component){
		canvas.setCenter(component.getView());
		currentCenterView = component;
		headerController.getTitleProperty().setValue(currentCenterView.getBreadcrumb().getValue());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void injectBusiness(IBusiness business) { UserFacade.business = business; }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startApplication(String[] args) {
		Application.launch(JavaFX.class, args);
	}
}