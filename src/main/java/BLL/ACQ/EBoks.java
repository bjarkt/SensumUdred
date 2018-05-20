package BLL.ACQ;


import java.io.IOException;
import java.util.*;

public class EBoks implements IEBoks {
    private IHttp httpClient;

    public EBoks(IHttp httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean sendMeetingMessage(Collection<IUser> participants, GregorianCalendar meetingDate, String info) {
        List<Integer> participantSSNs = new ArrayList<>();
        Map<String, Object> query = new HashMap<>();
        for (IUser participant : participants) {
            participantSSNs.add(Integer.parseInt(participant.getSocialSecurityNumber()));
        }

        if (meetingDate != null) {
            long unixTime = meetingDate.getTimeInMillis() / 1000; // unix time does not include milliseconds
            query.put("meetingDate", unixTime);
        }
        query.put("participants", participantSSNs);
        query.put("info", info);

        String response = null;
        try {
            response = new String(httpClient.makeHttpRequest(
                    "https://sensumudred-api.herokuapp.com/eboks/send-message", query,
                    HttpMethod.POST, HttpAcceptType.JSON));
            return response.length() > 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean sendMessage(Collection<IUser> participants, String info) {
        return this.sendMeetingMessage(participants, null, info);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean sendCancelMeetingMessage(Collection<IUser> participants, GregorianCalendar meetingDate, String info) {
        return true;
    }
}
