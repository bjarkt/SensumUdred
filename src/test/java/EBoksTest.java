import ACQ.*;
import BLL.eboks.EBoks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EBoksTest {
    private IEBoks eboks;

    @BeforeEach
    public void initialize() {
        eboks = new EBoks(TestHelper.getHttpClient());
    }

    @Test
    public void sendMeetingMessageTest() {
        Set<IUser> participants = new HashSet<>();
        participants.add(TestHelper.createUser("1234"));
        participants.add(TestHelper.createUser("5678"));
        participants.add(TestHelper.createUser("9012"));

        boolean successfulMessage = eboks.sendMeetingMessage(participants, new GregorianCalendar(), "Meeting message test");

        assertTrue(successfulMessage);
    }

    @Test
    public void sendMessageTest() {
        Set<IUser> participants = new HashSet<>();
        participants.add(TestHelper.createUser("1234"));
        participants.add(TestHelper.createUser("5678"));
        participants.add(TestHelper.createUser("9012"));

        boolean successfulMessage = eboks.sendMessage(participants, "Meeting message test");

        assertTrue(successfulMessage);
    }

    @Test
    public void sendCancelMeetingMessageTest() {
        Set<IUser> participants = new HashSet<>();
        participants.add(TestHelper.createUser("1234"));
        participants.add(TestHelper.createUser("5678"));
        participants.add(TestHelper.createUser("9012"));

        boolean successfulMessage = eboks.sendCancelMeetingMessage(participants, new GregorianCalendar(), "Meeting message test");

        assertTrue(successfulMessage);
    }
}