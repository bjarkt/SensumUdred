import ACQ.*;
import ACQ.IHttp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EBoksTest {
    private IEBoks eboks;

    @BeforeEach
    public void initialize() {
        eboks = new EBoks(new IHttp() {
            @Override
            public byte[] makeHttpRequest(String urlString, Map<String, Object> query, HttpMethod method, HttpAcceptType acceptType) throws IOException {
                return DAL.http_request_utility.HttpRequestUtility.makeHttpRequest(urlString, query, method, acceptType);
            }
        });
    }

    @Test
    public void sendMeetingMessageTest() {
        Set<IUser> participants = new HashSet<>();
        participants.add(createUser("1234"));
        participants.add(createUser("5678"));
        participants.add(createUser("9012"));

        boolean successfulMessage = eboks.sendMeetingMessage(participants, new GregorianCalendar(), "Meeting message test");

        assertTrue(successfulMessage);
    }

    @Test
    public void sendMessageTest() {
        Set<IUser> participants = new HashSet<>();
        participants.add(createUser("1234"));
        participants.add(createUser("5678"));
        participants.add(createUser("9012"));

        boolean successfulMessage = eboks.sendMessage(participants, "Meeting message test");

        assertTrue(successfulMessage);
    }

    @Test
    public void sendCancelMeetingMessageTest() {
        Set<IUser> participants = new HashSet<>();
        participants.add(createUser("1234"));
        participants.add(createUser("5678"));
        participants.add(createUser("9012"));

        boolean successfulMessage = eboks.sendCancelMeetingMessage(participants, new GregorianCalendar(), "Meeting message test");

        assertTrue(successfulMessage);
    }

    private IUser createUser(String ssn) {
        return new IUser() {
            @Override
            public IAccount getAccount() {
                return null;
            }

            @Override
            public String getName() {
                return null;
            }

            @Override
            public String getFirstName() {
                return null;
            }

            @Override
            public String getLastName() {
                return null;
            }

            @Override
            public String getSocialSecurityNumber() {
                return ssn;
            }
        };
    }
}