package ACQ;

import java.util.Set;

public interface IElucidationService {
	/**
	 * Create an elucidation with given information.
	 * @param citizen the citizen it is about
	 * @param caseworkers the caseworkers on the elucidation
	 * @param inquiry the inquiry about/from the citizen
	 * @return the built elucidation
	 */
	IElucidation createElucidation(IUser citizen, Set<IUser> caseworkers, IInquiry inquiry);

	/**
	 * Set the state of an elucidation.
	 * It can either be in open state or closed state.
	 * @param id the identifier of the elucidation
	 * @param isclosed is it closed or open
	 * @return true, if successful; otherwise false
	 */
	boolean updateState(long id, boolean isclosed);

	/**
	 * Update the inquiry in an elucidation.
	 * @param id the identifier of the elucidation
	 * @param inquiry the inquiry to update
	 * @return true, if successful; otherwise false
	 */
	boolean updateInquiry(long id, IInquiry inquiry);

	/**
	 * Update caseworkers.
	 * @param id the identifier of the elucidation
	 * @param users caseworkers assigned to the elucidation
	 * @return true, if successful; otherwise false
	 */
	boolean updateCaseworkers(long id, Set<IUser> users);

	/**
	 * Update task state.
	 * Changing the state will create and delete
	 * necessary and unnecessary objects.
	 * @param id the identifier of the elucidation
	 * @param state the new state of the task.
	 * @return true, if successful; otherwise false
	 */
	boolean updateTaskState(long id, ElucidationState state);

	/**
	 * Update citizen consent.
	 * @param id the identifier of the elucidation
	 * @param hasConsent has the citizen given a consent
	 * @return true, if successful; otherwise false
	 */
	boolean updateCitizenConsent(long id, boolean hasConsent);

	/**
	 * Update acting municipality.
	 * @param id the identifier of the elucidation
	 * @param municipality any municipality
	 * @return true, if successful; otherwise false
	 */
	boolean updateActingMunicipality(long id, String municipality);

	/**
	 * Update special circumstances.
	 * @param id the identifier of the elucidation
	 * @param newDescription any new description
	 * @return true, if successful; otherwise false
	 */
	boolean updateSpecialCircumstances(long id, String newDescription);

	/**
	 * Update guardian authority.
	 * @param id the identifier of the elucidation
	 * @param newDescription any new description
	 * @return true, if successful; otherwise false
	 */
	boolean updateGuardianAuthority(long id, String newDescription);

	/**
	 * Update total level of function.
	 * @param id the identifier of the elucidation
	 * @param letter the letter of the elucidation
	 * @return true, if successful; otherwise false
	 */
	boolean updateTotalLevelOfFunction(long id, char letter);

	/**
	 * Update offers.
	 * @param id the identifier of the elucidation
	 * @param offers the offers of the elucidation
	 * @return true, if successful; otherwise false
	 */
	boolean updateOffers(long id, Set<IOffer> offers);

	/**
	 * Update grantings.
	 * @param id the identifier of the elucidation
	 * @param grantings the grantings of the elucidation
	 * @return true, if successful; otherwise false
	 */
	boolean updateGrantings(long id, Set<IGranting> grantings);

	/**
	 * Update themes.
	 * @param id the identifier of the elucidation
	 * @param themes the themes of the elucidation
	 * @return true, if successful; otherwise false
	 */
	boolean updateThemes(long id, Set<ITheme> themes);

	/**
	 * Update a meeting.
	 * @param id the elucidation identifier
	 * @param meeting a meeting within the elucidation
	 * @return true, if successful; otherwise false
	 */
	boolean updateMeeting(long id, IMeeting meeting);

	/**
	 *
	 * @param id the identifier of the elucidation
	 * @param attachments the attachments of the elucidation
	 * @return true, if successful; otherwise false
	 */
	boolean updateThirdPartyAttachments(long id, Set<IAttachment> attachments);

	/**
	 * Get an elucidation based on the elucidation identifier
	 * @param id the identifier of the elucidation
	 * @return an elucidation
	 */
	IElucidation getElucidation(long id);

	/**
	 * Get all open elucidations that is assigned to a ssn.
	 * @param ssn any ssn
	 * @return a set of all the elucidations assigned to the ssn
	 */
	Set<IElucidation> getOpenElucidationsFromSSN(String ssn);

	/**
	 * Get all closed elucidations that is assigned to a ssn.
	 * @param ssn any ssn
	 * @return a set of all the elucidations assigned to the ssn
	 */
	Set<IElucidation> getClosedElucidationsFromSSN(String ssn);
}