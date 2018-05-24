package ACQ;

import BLL.case_opening.third_party_information.IAttachment;

import java.util.Set;

public interface IElucidationService {
	/**
	 * Create an elucidation with given informations.
	 * @param citizen the citizen it is about
	 * @param caseworkers the caseworkers on the elucidation
	 * @param inquiry the inquiry about/from the citizen
	 * @param dialog a dialog... (WIP)
	 * @return the built elucidation
	 */
	IElucidation createElucidation(IUser citizen, Set<IUser> caseworkers, IInquiry inquiry, IDialog dialog);

	/**
	 * Update the inquiry in an elucidation.
	 * @param id the identifier of the elucidation
	 * @param newDescription any new description
	 * @return true, if successful; otherwise false
	 */
	boolean updateInquiryDescription(long id, String newDescription);

	/**
	 * Update caseworkers.
	 * @param id the identifier of the elucidation
	 * @param users caseworkers assigned to the elucidation
	 * @return true, if successful; otherwise false
	 */
	boolean updateCaseworkers(long id, IUser ... users);

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
	boolean updateOffers(long id, IOffer ... offers);

	/**
	 * Update grantings.
	 * @param id the identifier of the elucidation
	 * @param grantings the grantings of the elucidation
	 * @return true, if successful; otherwise false
	 */
	boolean updateGrantings(long id, IGranting ... grantings);

	/**
	 * Update themes.
	 * @param id the identifier of the elucidation
	 * @param themes the themes of the elucidation
	 * @return true, if successful; otherwise false
	 */
	boolean updateThemes(long id, ITheme ... themes);

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
	boolean updateThirdPartyAttachments(long id, IAttachment ... attachments);

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