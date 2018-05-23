package UI.primary_view;

import ACQ.IProfile;
import ACQ.IUser;
import BLL.IBusiness;
import BLL.security_system.SecurityLevel;
import UI.IUserInterface;
import UI.JavaFX;
import UI.SecuredAspect;
import UI.components.IComponent;
import UI.components.all_elucidations_view.HomeViewController;
import UI.components.all_elucidations_view.IHomeView;
import UI.components.drawer.DrawerController;
import UI.components.drawer.IDrawer;
import UI.components.drawer.IDrawerRequire;
import UI.components.elucidation_view.ElucidationViewController;
import UI.components.elucidation_view.IElucidationView;
import UI.components.header.HeaderController;
import UI.components.header.IHeader;
import UI.components.log_in_page.ILogInView;
import UI.components.log_in_page.LogInViewController;
import UI.components.popUp.IPopUpRequire;
import UI.components.popUp.IPopup;
import UI.components.popUp.PopUpController;
import UI.components.user_menu.IUserMenu;
import UI.components.user_menu.UserMenuController;
import UI.components.vertical_menu.IVerticalMenu;
import UI.components.vertical_menu.VerticalMenuController;
import com.jfoenix.controls.JFXDrawer;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class UserFacade implements IUserInterface, Initializable {
	private static IBusiness business;
	private IProfile profile;

	private BooleanProperty isLoggedIn;

	private IHeader headerController;
	private ILogInView logInView;
	private IDrawer drawer;
	private IDrawer userDrawer;
	private IHomeView homeView;
	private IElucidationView elucidationView;
	private IPopup popUp;
	private IVerticalMenu verticalMenu;
	private IUserMenu userMenu;

	private boolean isMobile;

	/** Reference to the current center page component visible. */
	private IComponent currentCenterView;

	@FXML
	private AnchorPane screen;

	@FXML
	private BorderPane canvas;


	/**
	 * Constructor for UserFacade. Instantiates the needed components.
	 */
	public UserFacade(){
		SecuredAspect.setBusiness(business);
		SecuredAspect.setPopup(popUp);

		headerController = new HeaderController();
		logInView = new LogInViewController();
		homeView = new HomeViewController();

		verticalMenu = new VerticalMenuController();

		userMenu = new UserMenuController();

		elucidationView = new ElucidationViewController();

		drawer = new DrawerController(new IDrawerRequire() {
			@Override
			public AnchorPane getParent() {
				return screen;
			}
			@Override
			public Parent getContent() {
				return verticalMenu.getView();
			}
		}, JFXDrawer.DrawerDirection.LEFT, "Menu");

		userDrawer = new DrawerController(new IDrawerRequire() {
			@Override
			public AnchorPane getParent() {
				return screen;
			}

			@Override
			public Parent getContent() {
				return userMenu.getView();
			}
		}, JFXDrawer.DrawerDirection.RIGHT, "Brugerprofil");

		popUp = new PopUpController(new IPopUpRequire() {
			@Override
			public AnchorPane getParent() { return screen; }
		});

		isLoggedIn = new SimpleBooleanProperty();
		isLoggedIn.setValue(false);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/* Setup listeners for components */
		setupUpLoginView();
		setupHeader();
		setupVerticalMenu();
		setupAllElucidationsView();
		setupElucidationView();

		/* Set initial view to be log in view */
		setCenter(logInView);


		/* Only present certain UI elements if user is logged in */
		isLoggedIn.addListener((loginObservable, oldLoginValue, newLoginValue) -> {
			if(newLoginValue){
				canvas.setTop(headerController.getView());
				canvas.setLeft(verticalMenu.getView());
				canvas.getLeft().getStyleClass().add("canvas_left");
				setCenter(homeView);
				setupUserMenu();
				verticalMenu.setMyElucidationsButtonActive();

				/* Move vertical menu to drawer if screen is too small. */
				screen.widthProperty().addListener((observable, oldValue, newValue) -> {
					if(isMobile && newValue.doubleValue() > 800){
						isMobile = false;
						drawer.clearContent();
						headerController.disableHamburger();
						drawer.close();
						canvas.setLeft(verticalMenu.getView());
					} else if(!isMobile && newValue.doubleValue() < 800){
						isMobile = true;
						canvas.setLeft(null);
						headerController.enableHamburger();
						drawer.open();
						drawer.setContent();
					}
				});

			} else{
				canvas.setTop(null);
				canvas.setLeft(null);
				canvas.setTop(null);
				userDrawer.close();
				drawer.close();
			}
		});


	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void shutdown() {
		if (profile != null && profile.getAccount() != null) {
			business.getSigningService().signOut(profile.getAccount().getUsername());
		}
	}

	/**
	 * Loads the center of the screen with a component.
	 * @param component Component to be loaded into center view.
	 */
	private void setCenter(IComponent component){
		if(component == null) {
			canvas.setCenter(null);
			headerController.getTitleProperty().setValue("Page is not available");
		}
		else{
			canvas.setCenter(component.getView());
			currentCenterView = component;
			if(canvas.getTop() != null)	headerController.getTitleProperty().setValue(currentCenterView.getBreadcrumb().getValue());
		}
	}

	private void setupHeader(){
		headerController.onMenuClick(data -> {
			drawer.open();
		});

		headerController.onMessagesClick(data -> {

		});

		headerController.onProfileClick(data -> {
			userDrawer.open();
			userDrawer.setContent();
			userMenu.setUsersName(profile.getUser().getName());
		});
	}

	private void setupVerticalMenu(){
		verticalMenu.onLogClick(data -> {
			if(isMobile) drawer.close();
			popUp.show("Ikke implementeret.", "Hændelseslog er ikke tilgængelig endnu.");
			setCenter(null);
		});

		verticalMenu.onMyElucidationsClick(data -> {
			if(isMobile) drawer.close();
			setCenter(homeView);
		});

		verticalMenu.onUserManagement(data -> {
			business.setSecurityEventListener(data1 -> {
				popUp.show("Manglende rettigheder", "Du har ikke rettigheder til at tilgå brugeradministration.");
			});
			business.getUserManager();
			if(isMobile) drawer.close();
			setCenter(null);
		});

	}

	private void setupUserMenu(){
		userMenu.onLogOut(data -> {
			business.getSigningService().signOut(profile.getAccount().getUsername());
			isLoggedIn.setValue(false);
			logInView = new LogInViewController();
			setupUpLoginView();
			setCenter(logInView);
		});
	}

	private void setupUpLoginView(){
		logInView.onLogIn(data -> {
			profile = business.getSigningService().signIn(data[0], data[1]);
			if(profile != null && profile.getAccount() != null){
				SecuredAspect.setAccount(profile.getAccount());
				isLoggedIn.setValue(true);
			} else{
				logInView.writeError("Brugernavn eller password er forkert.");
			}
		});
	}

	private void setupAllElucidationsView(){
		homeView.onElucidationClick(data -> {
			setCenter(elucidationView);
		});
	}

	private void setupElucidationView(){
		elucidationView.onLeaveElucidation(data -> {
			setCenter(homeView);
		});

		elucidationView.onCaseSaveDescription(data -> {
			System.out.println(data);
		});

		elucidationView.onAddNewOffer(data -> {
			System.out.println(data);
		});
		elucidationView.onCaseCitizenInformation(data -> {
			System.out.println(data);
		});
		elucidationView.onCaseCitizenMunicipality(data -> {
			System.out.println(data);
		});
		elucidationView.onCaseCitzenAgreement(data -> {
			System.out.println(data);
		});
		elucidationView.onCaseSaveDescription(data -> {
			System.out.println(data);
		});
		elucidationView.onDeleteOffers(data -> {
			System.out.println(data);
		});
		elucidationView.onCaseSpecialCircumstancesField(data -> {
			System.out.println(data);
		});
		elucidationView.onLeaveElucidation(data -> {
			System.out.println(data);
		});

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