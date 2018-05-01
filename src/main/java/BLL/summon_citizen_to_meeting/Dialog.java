package BLL.summon_citizen_to_meeting;

import java.util.HashSet;
import java.util.Set;

public class Dialog {
    private Set<IMeeting> meetings;

    public Dialog() {
        meetings = new HashSet<>();
    }

    public IMeeting createMeeting() {
        IMeeting meeting = new Meeting();
        meetings.add(meeting);
        return meeting;
    }
}
