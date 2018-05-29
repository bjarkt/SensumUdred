import ACQ.IElucidation;
import ACQ.IElucidationService;
import BLL.account_system.Address;
import BLL.eboks.EBoks;
import BLL.getter.address_getter.GetAddress;
import BLL.mediators.ElucidationServiceMediator;
import DAL.IPersistent;
import DAL.PersistentFacade;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServiceMediatorTest {

    @Test
    public void TestTest() {
        IPersistent persistent = new PersistentFacade();
        IElucidationService elucidationService = new ElucidationServiceMediator(persistent.getDatabaseService().getElucidationService(),
                persistent.getHttp(),
                new EBoks(persistent.getHttp()),
                new GetAddress(persistent.getHttp(), Address.class));

        Set<IElucidation> elucidations = elucidationService.getOpenElucidationsFromSSN("1103971427");
        System.out.println(elucidations);

    }
}