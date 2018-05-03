import BLL.BusinessFacade;
import BLL.IBusiness;
import BLL.account_system.ILoginService;
import BLL.account_system.LoginService;
import BLL.account_system.UserManager;
import DAL.IPersistent;
import DAL.PersistentFacade;
import UI.IUserInterface;
import UI.primary_view.UserFacade;

public class Main {
    public static void main(String[] args) {
        IPersistent persistent = new PersistentFacade();
        IBusiness business = new BusinessFacade();
        IUserInterface ui = new UserFacade();

        ui.injectBusiness(business);
        business.injectPersistent(persistent);

        ui.startApplication(args);


        UserManager userManager = new UserManager();
        userManager.createUser("Lasse", "Traberg-Larsen", "1103971234", "28499228", "lasse","traberg");
        userManager.createUser("Bjarke", "Tobiesen", "1234567890", "88888888", "bjarke","tobiesen");
        userManager.createUser("Dennis", "Petersen", "1234567890", "88888888", "dennis","petersen");

        ILoginService loginService = new LoginService();
        loginService.login("Lasse", "traberg");


    }
}