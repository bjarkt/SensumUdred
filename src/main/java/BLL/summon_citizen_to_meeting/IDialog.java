package BLL.summon_citizen_to_meeting;

public interface IDialog {
    /**
     * Create a new meeting and add it to the collection of meetings
     * @return new IMeeting
     */
    IMeeting createMeeting();
}
