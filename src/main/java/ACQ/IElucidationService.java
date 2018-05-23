package ACQ;

import BLL.case_opening.third_party_information.IAttachment;
import BLL.meeting.IDialog;

import java.util.Set;

public interface IElucidationService {
	/**
	 * Create a elucidation
	 * @param elucidation
	 * @return
	 */
	IElucidation createElucidation(IUser citizen, Set<IUser> caseworkers, IInquiry inquiry, IDialog dialog);

	boolean updateInqueryDescription(long id, String newDescription);

	boolean updateCaseworkers(long id, IUser ... users);

	boolean updateCitizenConsent(long id, boolean hasConsent);

	boolean updateActingMunicipality(long id, String municipality);

	boolean updateSpecialCircumstances(long id, String newDescription);

	boolean updateGuardianAuthority(long id, String newDescription);

	boolean updateTotalLevelOfFunction(long id, char letter);

	boolean updateOffers(long id, IOffer ... offers);

	boolean updateGranting(long id, IGranting ... grantings);

	boolean updateThemes(long id, ITheme ... themes);

	boolean updateThirdPartyAttachments(long id, IAttachment ... attachments);

	IElucidation getElucidation(long id);
}