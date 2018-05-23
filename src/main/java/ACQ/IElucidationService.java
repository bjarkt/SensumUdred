package ACQ;

import BLL.case_opening.third_party_information.IAttachment;

public interface IElucidationService {
	boolean createElucidation(IElucidation elucidation);



	boolean updateInqueryDescription(long id, String newDescription);

	boolean updateOffers(long id, IOffer ... offers);

	boolean updateGranting(long id, IGranting grantings);

	boolean updateCaseworkers(long id, IUser ... users);

	boolean updateCitizenConsent(long id, boolean hasConsent);

	boolean updateActingMunicipality(long id, String municipality);

	boolean updateSpecialCircumstances(long id, String newDescription);

	boolean updateGuardianAuthority(long id, String newDescription);

	boolean updateTotalLevelOfFunction(long id, char letter);

	boolean updateThemes(long id, ITheme ... themes);

	boolean updateThirdPartyAttachments(long id, IAttachment ... attachments);

	IElucidation getElucidation(long id);
}