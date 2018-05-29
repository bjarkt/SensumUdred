package BLL;

import ACQ.*;
import BLL.Inquiry.Inquiry;
import BLL.account_system.Address;
import BLL.account_system.User;
import BLL.account_system.UserManager;
import BLL.eboks.EBoks;
import BLL.getter.address_getter.GetAddress;
import BLL.getter.address_getter.IGetAddress;
import BLL.getter.user_getter.GetUser;
import BLL.getter.user_getter.IGetUser;
import BLL.log_system.LogAspect;
import BLL.mediators.DefaultServiceMediator;
import BLL.mediators.ElucidationServiceMediator;
import BLL.mediators.SigningServiceMediator;
import BLL.security_system.SecuritySystem;
import BLL.theme_manager.IThemeManager;
import BLL.theme_manager.ThemeManager;
import DAL.IPersistent;

import java.util.Set;

public class BusinessFacade implements IBusiness {
	private IPersistent persistent;
	private IUserManager userManager;
	private IAdminService adminService;

	private IElucidationService elucidationServiceMediator;
	private IDefaultService defaultServiceMediator;
	private ILoggingService loggingService;

	public BusinessFacade() { }

	/**
	 * {@inheritDoc}
	 */
	@SecurityLevel(1000)
	@Override
	public IAdminService getAdminService() {
		return adminService;
	}

	/**
	 * {@inheritDoc}
	 */
	@SecurityLevel(0)
	@Override
	public IDefaultService getDefaultService() {
		return defaultServiceMediator;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IElucidationService getElucidationService() {
		return elucidationServiceMediator;
	}

	@SecurityLevel(500)
	@Override
	public ILoggingService getLoggingService() {
		return loggingService;
	}

	@Override
	@SecurityLevel(0)
	public ILogMakerService getLogMakerService() {
		return loggingService;
	}

	@SecurityLevel(500)
	@Override
	public ILogGetterService getLogGetterService() {
		return loggingService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IElucidation getElucidation(int ID) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IUser getUser(String ssn) {
		IUser user = null;

		if(persistent.getDatabaseService().getDefaultService().userExists(ssn)) {
			user = persistent.getDatabaseService().getDefaultService().getUser(ssn);
		}

		return user;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IUser createUser(String ssn, String firstName, String lastName, IAddress address, String phone, String email) {
		User user = new User(ssn, firstName, lastName);
		user.setAddress((Address)address);
		user.setPhoneNumber(phone);
		user.setEmail(email);

		userManager.signUpUser(ssn, firstName, lastName, phone, email);

		return user;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IUserManager getUserManager() {
		return userManager;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setSecurityEventListener(IEventListener<SecurityException> eventListener) {
		SecuritySystem.getInstance().setEventListener(eventListener);
	}

	/**
	 * {@inheritDoc}
	 */
	@SecurityLevel(0)
	@Override
	public Set<IElucidation> getMyElucidations() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@SecurityLevel(500)
	@Override
	public IInquiry createInquiry(String description, String source) {
		return new Inquiry(description, source);
	}

	/**
	 * {@inheritDoc}
	 */
	@SecurityLevel(500)
	@Override
	public Set<IUser> searchUsersContaining(String query) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@SecurityLevel(500)
	@Override
	public void addCaseworkerToCase(IUser user) {

	}

	/**
	 * {@inheritDoc}
	 */
	@SecurityLevel(500)
	@Override
	public IThemeManager getThemeManager(ICase aCase) {
		return new ThemeManager(aCase);
	}

	/**
	 * {@inheritDoc}
	 */
	@SecurityLevel(500)
	@Override
	public IGetUser getGetUser() {
		return new GetUser(persistent.getHttp(), User.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IGetAddress getGetAddress() {
		return new GetAddress(persistent.getHttp(), Address.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void injectPersistent(IPersistent persistent) {
		this.persistent = persistent;

		IDatabaseService service = persistent.getDatabaseService();

		this.defaultServiceMediator = new DefaultServiceMediator(service.getDefaultService(), getGetAddress());
		this.adminService = service.getAdminService();
		this.loggingService = service.getLoggingService();
		this.userManager = new UserManager(new DefaultServiceMediator(service.getDefaultService(), getGetAddress()), new SigningServiceMediator(service.getSigningService(), getGetAddress()));
		this.elucidationServiceMediator = new ElucidationServiceMediator(service.getElucidationService(), persistent.getHttp(), new EBoks(persistent.getHttp()), getGetAddress());

		LogAspect.setLoggingService(service.getLoggingService());
	}
}