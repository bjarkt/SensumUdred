package BLL.meeting;


import BLL.ACQ.HttpAcceptType;
import BLL.ACQ.HttpMethod;
import BLL.ACQ.IEBoks;
import BLL.ACQ.IUser;

import java.io.IOException;
import java.util.*;

public class EBoksImpl implements IEBoks {
    private IHttp httpClient;

    public EBoksImpl(IHttp httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean sendMeetingMessage(Collection<IUser> participants, GregorianCalendar meetingDate, String info) {
        List<Integer> participantSSNs = new ArrayList<>();
        for (IUser participant : participants) {
            participantSSNs.add(Integer.parseInt(participant.getSocialSecurityNumber()));
        }
        long unixTime = meetingDate.getTimeInMillis() / 1000; // unix time does not include milliseconds


        Map<String, Object> query = new HashMap<>();
        query.put("participants", participantSSNs);
        query.put("meetingDate", unixTime);
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
    public boolean sendCancelMeetingMessage(Collection<IUser> participants, GregorianCalendar meetingDate, String info) {
        return true;
    }
}
