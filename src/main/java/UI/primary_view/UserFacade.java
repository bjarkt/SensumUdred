package UI.primary_view;

import ACQ.IElucidation;
import ACQ.IInquiry;
import ACQ.IProfile;
import ACQ.IUser;
import BLL.IBusiness;
import UI.IUserInterface;
import UI.JavaFX;
import UI.SecuredAspect;
import UI.components.IComponent;
import UI.components.all_elucidations_view.HomeViewController;
import UI.components.all_elucidations_view.IHomeView;
import UI.components.data_prompt.DataPromptController;
import UI.components.data_prompt.IDataPrompt;
import UI.components.drawer.DrawerController;
import UI.components.drawer.IDrawer;
import UI.components.drawer.IDrawerRequire;
import UI.components.elucidation_view.ElucidationViewController;
import UI.components.elucidation_view.IElucidationView;
import UI.components.elucidation_view.theme.IThemeUI;
import UI.components.header.HeaderController;
import UI.components.header.IHeader;
import UI.components.log_in_page.ILogInView;
import UI.components.log_in_page.LogInViewController;
import UI.components.meetingPopUp.IMeetingPopUp;
import UI.components.meetingPopUp.IMeetingPopUpRequire;
import UI.components.meetingPopUp.MeetingPopUpController;
import UI.components.popUp.IPopUpRequire;
import UI.components.popUp.IPopup;
import UI.components.popUp.PopUpController;
import UI.components.send_popUp.ISendPopUpRequire;
import UI.components.send_popUp.ISendPopup;
import UI.components.send_popUp.SendPopUpController;
import UI.components.user_menu.IUserMenu;
import UI.components.user_menu.UserMenuController;
import UI.components.vertical_menu.IVerticalMenu;
import UI.components.vertical_menu.VerticalMenuController;
import UI.task.Task;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXSpinner;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

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
	private ISendPopup sendPopup;

	private boolean isMobile;

	// Wrapper for loading spinner.
	private StackPane spinnerWrapper;

	// Boolean attribute holding spinning state.
	private boolean isSpinning;

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
		sendPopup = new SendPopUpController(new ISendPopUpRequire() {
			@Override
			public AnchorPane getParent() {
				return screen;
			}
		});

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
		// Setup listeners for components
		setupUpLoginView();
		setupHeader();
		setupVerticalMenu();
		setupAllElucidationsView();
		setupElucidationView();

		// Set initial view to be log in view
		setCenter(logInView);

		// Displays loading spinner when background loading tasks are running.
		Task.onRunningTasksChanged((observable, oldValue, newValue) -> {
			Platform.runLater(() -> {
				if(newValue.intValue() > 0) startSpinner();
				else stopSpinner();
			});
		});

		/* Only present certain UI elements if user is logged in */
		isLoggedIn.addListener((loginObservable, oldLoginValue, newLoginValue) -> {
			if(newLoginValue){
				canvas.setTop(headerController.getView());
				canvas.setLeft(verticalMenu.getView());
				canvas.getLeft().getStyleClass().add("canvas_left");
				setCenter(homeView);
				tickMyElucidations();
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
			business.getUserManager().signOut(profile.getAccount().getUsername());
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
			userMenu.setUserID(profile.getAccount().getUsername());
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
			tickMyElucidations();
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

	// Setup the user drawer with event handlers.
	private void setupUserMenu(){
		userMenu.onLogOut(data -> {
			business.getUserManager().signOut(profile.getAccount().getUsername());
			isLoggedIn.setValue(false);
			logInView = new LogInViewController();
			setupUpLoginView();
			setCenter(logInView);
		});
	}


	// Set up the login view with eventhandlers and calls to domain layer.
	private void setupUpLoginView(){
		logInView.onLogIn(data -> {
			Task<IProfile> task = new Task<>(new Supplier<IProfile>() {
				@Override
				public IProfile get() {
					return business.getUserManager().signIn(data[0], data[1]);

				}
			});

			task.setOnSucceeded(data1 -> {
				if(data1 != null){
					profile = data1;
					SecuredAspect.setAccount(data1.getAccount());
					Platform.runLater(() -> {
						homeView = new HomeViewController();
						setupAllElucidationsView();
						isLoggedIn.setValue(true);
					});
				} else{
					Platform.runLater(() -> {
						logInView.writeError("Brugernavn eller adgangskode er forkert.");
					});
				}
			});
		});
	}

	private void setupAllElucidationsView(){
		homeView.onElucidationClick(data -> {
			elucidationView = new ElucidationViewController();
			setupElucidationView();
			setCenter(elucidationView);
		});

		homeView.onNewInquiry(data -> {
			AtomicReference<IUser> citizen = new AtomicReference<>();
			AtomicReference<String> inquiryStr = new AtomicReference<>();
			Set<IUser> caseworkers = new HashSet<>();
			caseworkers.add(profile.getUser());

			IDataPrompt dataPrompt = new DataPromptController();
			setCenter(dataPrompt);
			dataPrompt.setPrompt("Hvem er borgeren?");
			dataPrompt.addTextFields("CPR-nummer", "Fornavn", "Efternavn", "Email", "Telefon");
			dataPrompt.onContinue(data1 -> {
				System.out.println(data1);
				IDataPrompt dataPrompt2 = new DataPromptController();
				setCenter(dataPrompt2);
				dataPrompt2.setPrompt("Beskriv henvendelsen, og kilde");
				dataPrompt2.addTextFields("Om henvendelsen", "Kilde til henvendelse");
				dataPrompt2.onContinue(data2 -> {
					IInquiry inq = business.createInquiry(data2.get(0), data2.get(1));
					citizen.set(business.getUser(data1.get(0)));

					// Does the user exist?
					if (business.getUser(data1.get(0)) == null) {
						citizen.set(business.createUser(data1.get(0), data1.get(1), data1.get(2), null, data1.get(3), data1.get(4)));
					} else { // if user exists, use data from DB
						citizen.set(business.getUser(data1.get(0)));
					}

					business.getElucidationService().createElucidation(citizen.get(), caseworkers, inq);
					setCenter(homeView);
				});
			});
		});
	}

	private void setupElucidationView(){

		elucidationView.onSendMessage(data -> {
			sendPopup.getView();
			sendPopup.show();
		});

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

		elucidationView.onCreateMeeting(data -> {
			IMeetingPopUp meetingPopUp = new MeetingPopUpController(new IMeetingPopUpRequire() {
				@Override
				public AnchorPane getParent() {
					return screen;
				}
			});
			meetingPopUp.show("Indkald til møde", "Data her");

			meetingPopUp.getDropdownSearch().updateList(business.getDefaultService().getAllUsers(2));


		});

		elucidationView.onAddNewTheme(newThemes -> {
			for (IThemeUI theme : newThemes) {
				System.out.println(theme.getTheme().getName() + ", " + theme.getSubtheme() + ", " + theme.getDocumentation() + ", " + theme.getLevelOfFunction());
			}
		});

		elucidationView.onDeleteTheme(deletedThemes -> {
			for (IThemeUI theme : deletedThemes) {
				if (theme.getTheme() != null) {
					System.out.println(theme.getTheme().getName() + ", " + theme.getSubtheme() + ", " + theme.getDocumentation() + ", " + theme.getLevelOfFunction());
				}
			}
		});

	}

	/**
	 * Starts loading spinner to visualize a present call to database.
	 */
	private void startSpinner(){
		if(isSpinning == false) {
			isSpinning = true;
			JFXSpinner spinner = new JFXSpinner();
			spinner.setRadius(12);
			spinnerWrapper = new StackPane(spinner);
			screen.getChildren().add(spinnerWrapper);
			screen.setLeftAnchor(spinnerWrapper, 0.);
			screen.setRightAnchor(spinnerWrapper, 0.);
			screen.setBottomAnchor(spinnerWrapper, 20.);
		}
	}

	private void stopSpinner(){
		isSpinning = false;
		screen.getChildren().remove(spinnerWrapper);
	}

	// Method to update all the user's elucidation.
	private void tickMyElucidations(){
		Task<Set<IElucidation>> loadElucidationsTask = new Task<>(new Supplier<Set<IElucidation>>() {
			@Override
			public Set<IElucidation> get() {
				Platform.runLater(() -> homeView.disableList());
				return business.getElucidationService().getOpenElucidationsFromSSN(profile.getUser().getSocialSecurityNumber());
			}
		});

		loadElucidationsTask.setOnSucceeded(data1 -> {
			Platform.runLater(() -> {
				homeView.tickList(data1);
				homeView.enableList();
			});
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