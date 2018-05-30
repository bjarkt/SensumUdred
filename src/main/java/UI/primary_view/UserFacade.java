package UI.primary_view;

import ACQ.*;
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
import UI.components.elucidation_view.IElucidationViewRequire;
import UI.components.elucidation_view.theme.IThemeUI;
import UI.components.header.HeaderController;
import UI.components.header.IHeader;
import UI.components.log_in_page.ILogInView;
import UI.components.log_in_page.LogInViewController;
import UI.components.meetingPopUp.IMeetingPopUp;
import UI.components.meetingPopUp.IMeetingPopUpRequire;
import UI.components.meetingPopUp.MeetingPopUpController;
import UI.components.meetings_view.IMeetingsView;
import UI.components.meetings_view.MeetingsViewController;
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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

public class UserFacade implements IUserInterface, Initializable {
	private static IBusiness business;
	private IProfile profile;
	private IElucidation elucidation;

	private BooleanProperty isLoggedIn;

	private IHeader headerController;
	private ILogInView logInView;
	private IDrawer drawer;
	private IDrawer userDrawer;
	private IHomeView homeView;
	private IMeetingsView meetingsView;
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
		isLoggedIn = new SimpleBooleanProperty();
		isLoggedIn.setValue(false);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Set initial view to be log in view
		logInView = new LogInViewController();
		setCenter(logInView);
		setupUpLoginView();

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

				//region initialize controllers
				headerController = new HeaderController();
				homeView = new HomeViewController();

				verticalMenu = new VerticalMenuController();

				userMenu = new UserMenuController();

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

				sendPopup = new SendPopUpController(new ISendPopUpRequire() {
					@Override
					public AnchorPane getParent() {
						return screen;
					}
				});

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
				//endregion

				//region setup listeners
				setupHeader();
				setupVerticalMenu();
				setupAllElucidationsView();
				//endregion

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
		verticalMenu.onMyMeetingsClick(data -> {
			if(isMobile) drawer.close();
			meetingsView = new MeetingsViewController();
			setCenter(meetingsView);
			tickMyMeetings();
		});

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
			shutdown();
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

		// Instantiate and switch to elucidation view when user selects elucidation.
		homeView.onElucidationClick(data -> {
			if(data != null) {
				elucidationView = new ElucidationViewController(new IElucidationViewRequire() {
					@Override
					public IElucidation getElucidation() {
						return data;
					}
				});
				this.elucidation = data;
				setCenter(elucidationView);
				setupElucidationView(data);
			}
		});

		homeView.onNewInquiry(data -> {
			AtomicReference<IUser> citizen = new AtomicReference<>();
			AtomicReference<String> inquiryStr = new AtomicReference<>();
			Set<IUser> caseworkers = new HashSet<>();
			caseworkers.add(profile.getUser());

			IDataPrompt dataPrompt = new DataPromptController();
			setCenter(dataPrompt);
			dataPrompt.setPrompt("Hvem er borgeren?");
			dataPrompt.addTextFields("CPR-nummer", "Fornavn", "Efternavn", "Telefon", "Email");
			dataPrompt.onContinue(data1 -> {
				System.out.println(data1);
				IDataPrompt dataPrompt2 = new DataPromptController();
				setCenter(dataPrompt2);
				dataPrompt2.setPrompt("Beskriv henvendelsen, og kilde");
				dataPrompt2.addTextFields("Om henvendelsen", "Kilde til henvendelse");
				dataPrompt2.onContinue(data2 -> {
					setCenter(homeView);
					IInquiry inq = business.createInquiry(data2.get(0), data2.get(1));
					// Does the user exist?
					if (business.getUser(data1.get(0)) == null) {
						citizen.set(business.createUser(data1.get(0), data1.get(1), data1.get(2), null, data1.get(3), data1.get(4)));
					} else { // if user exists, use data from DB
						citizen.set(business.getUser(data1.get(0)));
					}

					Task<IElucidation> task = new Task<>(new Supplier<IElucidation>() {
						@Override
						public IElucidation get() {
							return business.getElucidationService().createElucidation(citizen.get(), caseworkers, inq);

						}
					});

					task.setOnSucceeded(returnData -> {
						if(returnData != null) {
							Platform.runLater(() -> {
								tickMyElucidations();
							});
						}
					});










				});
			});
		});
	}

	private void setupElucidationView(IElucidation elucidation){


		// Setup listener for when caseworker upgrades task state to case.
		elucidationView.onToggleState(data -> {
			canvas.setCenter(new Label("Opgraderer udredning til sag..."));
			Task<Boolean> task = new Task<>(new Supplier<Boolean>() {
				@Override
				public Boolean get() {
					return business.getElucidationService().updateTaskState(elucidation.getId(), ElucidationTaskState.CASE);
				}
			});
			task.setOnSucceeded(data1 -> {
				Task<IElucidation> task2 = new Task<>(new Supplier<IElucidation>() {
					@Override
					public IElucidation get() {
						return business.getElucidationService().getElucidation(elucidation.getId());
					}
				});
				task2.setOnSucceeded(data2 -> {
					Platform.runLater(() -> {
						elucidationView = new ElucidationViewController(new IElucidationViewRequire() {
							@Override
							public IElucidation getElucidation() {
								return data2;
							}
						});
						setCenter(elucidationView);
						setupElucidationView(data2);
						popUp.show("Sag oprettet.", "Der er nu oprettet en konkret sag for sagsnummer " + elucidation.getId());
					});
				});
			});

		});

		/* Setup listener for when caseworkers closes elucidation. */
		elucidationView.onCloseCase(data -> {
			setCenter(homeView);

			Task<Boolean> task = new Task<>(new Supplier<Boolean>() {
				@Override
				public Boolean get() {
					return business.getElucidationService().updateState(elucidation.getId(), true);
				}
			});

			task.setOnSucceeded(data1 -> {
				Platform.runLater(() -> {
					popUp.show("Sag lukket.", "Sagsnummer " + elucidation.getId() + " er nu blevet lukket.");
					tickMyElucidations();
				});
			});
		});

		/* Setup listener for when admin updates citizen's info. */
		elucidationView.onCaseCitizenInformation(data -> {

			Task<Boolean> task = new Task<>(new Supplier<Boolean>() {
				@Override
				public Boolean get() {
					return business.getAdminService().changeFirstName(data[0], (data[1].split("\\s+")[0]));
				}
			});
			task.setOnSucceeded(data1 -> {});

			Task<Boolean> task2 = new Task<>(new Supplier<Boolean>() {
				@Override
				public Boolean get() {
					return business.getAdminService().changeLastName(data[0], (data[1].split("\\s+")[1]));
				}
			});
			task2.setOnSucceeded(data1 -> {});

			Task<Boolean> task3 = new Task<>(new Supplier<Boolean>() {
				@Override
				public Boolean get() {
					return business.getAdminService().changePhoneNumber(data[0], data[3]);
				}
			});
			task3.setOnSucceeded(data1 -> {});

			Task<Boolean> task4 = new Task<>(new Supplier<Boolean>() {
				@Override
				public Boolean get() {
					return business.getAdminService().changeEmail(data[0], data[4]);
				}
			});
			task4.setOnSucceeded(data1 -> {});

			Task<Boolean> task5 = new Task<>(new Supplier<Boolean>() {
				@Override
				public Boolean get() {
					return business.getAdminService().changeSSN(data[0], data[2]);
				}
			});
			task5.setOnSucceeded(data1 -> {});
		});

		elucidationView.onLeaveElucidation(data -> {
			setCenter(homeView);
			tickMyElucidations();
		});

		/* Setup listener for when caseworker updates inquiry description. */
		elucidationView.onCaseSaveDescription(data -> {
			Task<Boolean> task = new Task<>(new Supplier<Boolean>() {
				@Override
				public Boolean get() {
					return business.getElucidationService().updateInquiry(elucidation.getId(), business.createInquiry(data, ((IInquiry)elucidation.getTask()).getSource()));
				}
			});

			task.setOnSucceeded(returnData -> {
				Platform.runLater(() -> {
					popUp.show("Gemt!", "Sagens beskrivelse blev gemt korrekt.");
				});
			});

		});

		elucidationView.onAddNewOffer(data -> {
			System.out.println(data);
		});

		elucidationView.onCaseCitizenMunicipality(data -> {
			System.out.println(data);
		});
		elucidationView.onCaseCitzenAgreement(data -> {
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
			meetingPopUp.show("Indkald til møde", "");

			meetingPopUp.getDropdownSearch().updateList(business.getDefaultService().getAllUsers(-1));

			meetingPopUp.onDone(data1 -> {
				IMeeting meeting = business.getElucidationService().getElucidation(elucidation.getId()).getDialog().createMeeting(profile.getUser());
				meeting.setInformation(meetingPopUp.getMeetingInformation());
				int[] yearMonthDayHourMin = meetingPopUp.getMeetingDate();
				meeting.setMeetingDate(yearMonthDayHourMin[0], yearMonthDayHourMin[1], yearMonthDayHourMin[2], yearMonthDayHourMin[3], yearMonthDayHourMin[4]);
				meetingPopUp.getChosenAttendees().forEach(iUser -> {
					meeting.addParticipant(iUser);
				});
				meeting.addParticipant(profile.getUser());
				business.getElucidationService().updateMeeting(elucidation.getId(), meeting);
			});

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
				Platform.runLater(() -> {
					homeView.disableList();
					canvas.setCenter(new Label("Indlæser dine sager..."));
				});
				return business.getElucidationService().getOpenElucidationsFromSSN(profile.getUser().getSocialSecurityNumber());
			}
		});

		loadElucidationsTask.setOnSucceeded(data1 -> {
			Platform.runLater(() -> {
				setCenter(homeView);
				setupAllElucidationsView();
				homeView.tickList(data1);
				homeView.enableList();
			});
		});
	}

	// Method to update all the user's elucidation.
	private void tickMyMeetings(){
		Set<IMeeting> meetingsToParse = new HashSet<>();

		Task<Set<IElucidation>> loadElucidationsTask = new Task<>(new Supplier<Set<IElucidation>>() {
			@Override
			public Set<IElucidation> get() {
				Platform.runLater(() -> {
					meetingsView.disableList();
					canvas.setCenter(new Label("Indlæser dine møder..."));
				});
				return business.getElucidationService().getOpenElucidationsFromSSN(profile.getUser().getSocialSecurityNumber());
			}
		});

		loadElucidationsTask.setOnSucceeded(data1 -> {
			for (IElucidation iElucidation : data1) {
				System.out.println("Her her");
				System.out.println(iElucidation.getDialog().getMeetings().size());
				for (IMeeting meeting : iElucidation.getDialog().getMeetings()) {
					meetingsToParse.add(meeting);
				}
			}
			Platform.runLater(() -> {
				setCenter(meetingsView);
				meetingsView.tickList(meetingsToParse);
				meetingsView.enableList();
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void shutdown() {
		if (profile != null && profile.getAccount() != null) {
			Task<Boolean> signOutTask = new Task<>(new Supplier<Boolean>() {
				@Override
				public Boolean get() {
					Platform.runLater(() -> {
						canvas.setLeft(null);
						canvas.setTop(null);
						userDrawer.close();
						canvas.setCenter(new Label("Logger ud..."));
					});
					if(profile != null) return business.getUserManager().signOut(profile.getAccount().getUsername());
					else return null;
				}
			});
			signOutTask.setOnSucceeded(data -> {
				Platform.runLater(() -> {
					isLoggedIn.setValue(false);
					profile = null;
					logInView = new LogInViewController();
					setupUpLoginView();
					setCenter(logInView);
				});
			});
		}
	}

}