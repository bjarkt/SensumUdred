package UI.components.usermanagement_view;

import ACQ.*;
import UI.components.IComponent;
import javafx.util.Pair;

import java.util.Set;

public interface IUserManagementView extends IComponent {

    void setIUser(IUser user);

    /**
     * Event listener for when load button is clicked.
     * @param listener event listener.
     */
    void onLoadData(IEventListener<String> listener);

    /**
     * Event listener for when first name is updated
     * @param listener event listener
     */
    void updateFirstName(IEventListener<Pair<String,String>> listener);

    /**
     * Event listener for when last name is updated
     * @param listener event listener
     */
    void updateLastName(IEventListener<Pair<String,String>> listener);

    /**
     * Event listener for when email is updated
     * @param listener event listener
     */
    void updateEmail(IEventListener<Pair<String,String>> listener);


    /**
     * Event listener for when phone number is updated.
     * @param listener event listener
     */
    void updatePhoneNumber(IEventListener<Pair<String,String>> listener);


    /**
     * Event listener for when ssn is updated.
     * @param listener event listener
     */
    void updateSSN(IEventListener<Pair<String,String>> listener);

    /**
     * Event listener for when a user created
     * @param listener event listener
     */
    void createUser(IEventListener<String[]> listener);

    /**
     * Event listener for updating user name.
     * @param listener event listener.
     */
    void updateUserName(IEventListener<Pair<String,String>> listener);

    /**
     * Event listener for updating password
     * @param listener  event listener
     */
    void updatePassword(IEventListener<Pair<String,String>> listener);

    /**
     * Event listener for creating account
     * @param listener  event listener
     */
    void createAccount(IEventListener<String[]> listener);

    /**
     * Event listener for activating account
     * @param listener  event listener
     */
    void activateAccount(IEventListener<Pair<String, Boolean>> listener);

    /**
     * Event listener for deactivating account
     * @param listener  event listener
     */
    void deactivateAccount(IEventListener<Pair<String, Boolean>> listener);

    /**
     * Event listener for updating securityLevel
     * @param listener  event listener
     */
    void updateSecurityLevel(IEventListener<Pair<String, Integer>> listener);


}
