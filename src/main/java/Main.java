import BLL.ACQ.HttpAcceptType;
import BLL.ACQ.HttpMethod;
import BLL.BusinessFacade;
import BLL.IBusiness;
import BLL.case_opening.CaseOpeningProvider;
import BLL.case_opening.ICaseOpeningService;
import BLL.case_opening.IHttp;
import DAL.IPersistent;
import DAL.PersistentFacade;
import UI.IUserInterface;
import UI.primary_view.UserFacade;

import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        IPersistent persistent = new PersistentFacade();
        IBusiness business = new BusinessFacade();
        IUserInterface ui = new UserFacade();

        ui.injectBusiness(business);
        business.injectPersistent(persistent);

        ui.startApplication(args);

    }
}