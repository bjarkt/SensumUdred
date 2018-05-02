import BLL.BusinessFacade;
import BLL.IBusiness;
import BLL.case_opening.CaseOpeningProvider;
import BLL.case_opening.third_party_information.ThirdPartyService;
import DAL.IPersistent;
import DAL.PersistentFacade;
import UI.IUserInterface;
import UI.primary_view.UserFacade;

public class Main {
    public static void main(String[] args) {
        IPersistent persistent = new PersistentFacade();
        IBusiness business = new BusinessFacade();
        IUserInterface ui = new UserFacade();

        new CaseOpeningProvider().requestThirdPartyCredentials(ThirdPartyService.HOSPITAL, 0);

        ui.injectBusiness(business);
        business.injectPersistent(persistent);

        ui.startApplication(args);
    }
}