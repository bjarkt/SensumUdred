package ACQ;

import ACQ.IUser;

import java.util.Date;
import java.util.Set;

public interface IInquiry {
    /**
     * Add one or more users to the inquiry
     * @param attendee one or more user
     */
    void addAttendee(IUser ...attendee);

    /**
     * Get one or more users that attends in the inquiry
     * @return The users attending in the inquiry
     */
    Set<IUser> getAttendee();

    /**
     * Remove an attendee from the inquiry
     * @param attendee the user to be removesd from the inquiry
     */
    void removeAttendee(IUser ...attendee);

    /**
     * get CPR linked to user
     * @return Users CPR
     */
    String getCPR();

    /**
     * set users CPR
     * @param CPR user CPR
     */
    void setCPR(String CPR);

    /**
     * get name linked to user
     * @return Users name
     */
    String getName();

    /**
     * Set name for user
     * @param name users name
     */
    void setName(String name);

    /**
     * Get the address of the user
     * @return user address
     */
    String getAddress();

    /**
     * Set the users address
     * @param address user address
     */
    void setAddress(String address);

    /**
     * Get inquiry's description
     * @return inquiry description
     */
    String getDescription();

    /**
     * Set inquiry's description
     * @param description inquiry description
     */
    void setDescription(String description);

    /**
     * Get the gender of the user
     * @return user gender
     */
    String getGender();

    /**
     * Set the users gender
     * @param gender user gender
     */
    void setGender(String gender);

    /**
     * Get the users birthdate
     * @return user birthdate
     */
    String getBirthDate();

    /**
     * Set the birthdate of the user
     * @param birthDate user birthdate
     */
    void setBirthDate(String birthDate);

    /**
     * Get the civilstatus of the user
     * @return user civilstatus
     */
    String getCivilStatus();

    /**
     * Set the civilstatus of the user
     * @param civilStatus user civilstatus
     */
    void setCivilStatus(String civilStatus);

    /**
     * get CPR linked to user
     * @return Users CPR
     */
    Date getRegistrationDate();

    /**
     * Set the registration date of the inquiry
     * @param registrationDate inquiry registration date
     */
    void setRegistrationDate(Date registrationDate);

    // add saveInquiry()

    // add editINquiry()
}
