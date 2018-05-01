import BLL.ACQ.IUser;
import BLL.BusinessFacade;
import BLL.IBusiness;
import BLL.access_system.AccessSystem;
import DAL.IPersistent;
import DAL.PersistentFacade;
import UI.IUserInterface;
import UI.primary_view.UserFacade;

public class Main {
    public static void main(String[] args) {
        IPersistent persistent = new PersistentFacade();
        IBusiness business = new BusinessFacade();
        IUserInterface ui = new UserFacade();

        AccessSystem.getINSTANCE().setUser(() -> 0);
        AccessSystem.getINSTANCE().init();

        ui.injectBusiness(business);
        business.injectPersistent(persistent);

        ui.startApplication(args);
    }
}