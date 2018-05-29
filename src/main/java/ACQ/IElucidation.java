package ACQ;

import java.util.Date;
import java.util.Set;

public interface IElucidation {

    /**
     * Get the id of the elucidation.
     * This identifier is unique to each elucidation.
     * @return id
     */
    long getId();

    /**
     * Get the applicant or citizen this elucidation involves.
     */
    IUser getCitizen();

    /**
     * Add one or more caseworkers to the elucidation.
     * @param caseworkers    one or more caseworkers.
     */
    void addCaseworker(IUser ...caseworkers);

    /**
     * Get the caseworkers on this elucidation.
     * @return  the caseworkers on this elucidation.
     */
    Set<IUser> getCaseworkers();

    /**
     * Get the task of the elucidation.
     * The task can tell if it is a {@link IInquiry} or {@link ICase}.
     * @return the task
     */
    ITask getTask();

    /**
     * Get the dialog object, which contains meetings.
     * @return dialog object
     */
    IDialog getDialog();

    /**
     * Get the time of opening the elucidation.
     * @return  time of opening.
     */
    Date getCreationDate();

}
