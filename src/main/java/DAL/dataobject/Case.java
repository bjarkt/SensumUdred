package DAL.dataobject;

import ACQ.*;

import java.util.Set;

public class Case extends Inquiry implements ICase {
	private boolean citizenConsent;
	private String specialCircumstances;
	private String guardianAuthority;
	private String actingMunicipality;
	private char totalLevelOfFunction;
	private Set<ITheme> themes;
	private Set<IOffer> offers;
	private Set<IGranting> grantings;
	private Set<IAttachment> attachments;

	public Case(String source, String description, ElucidationTaskState state) {
		super(source, description, state);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean getCitizenConsent() {
		return citizenConsent;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getSpecialCircumstances() {
		return specialCircumstances;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getActingMunicipality() {
		return actingMunicipality;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getGuardianAuthority() {
		return guardianAuthority;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public char getTotalLevelOfFunction() {
		return totalLevelOfFunction;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<ITheme> getThemes() {
		return themes;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<IOffer> getOffers() {
		return offers;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<IGranting> getGrantings() {
		return grantings;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<IAttachment> getThirdPartyInformations() {
		return attachments;
	}

	/**
	 * Set the citizen consent.
	 * @param citizenConsent if citizen has give consent true; otherwise false
	 */
	public void setCitizenConsent(boolean citizenConsent) {
		this.citizenConsent = citizenConsent;
	}

	/**
	 * Set the Special Circumstances.
	 * @param specialCircumstances String with special circumstances
	 */
	public void setSpecialCircumstances(String specialCircumstances) {
		this.specialCircumstances = specialCircumstances;
	}

	/**
	 * Set the Special Circumstances.
	 * @param actingMunicipality String with special circumstances
	 */
	public void setActingMunicipality(String actingMunicipality) {
		this.actingMunicipality = actingMunicipality;
	}

	/**
	 * Set the guardian authority.
	 * @param guardianAuthority String with guardian authority
	 */
	public void setGuardianAuthority(String guardianAuthority) {
		this.guardianAuthority = guardianAuthority;
	}

	/**
	 * Set the total level of function of the case.
	 * @param totalLevelOfFunction the level of the case
	 */
	public void setTotalLevelOfFunction(char totalLevelOfFunction) {
		this.totalLevelOfFunction = totalLevelOfFunction;
	}

	/**
	 * Set the themes of the case.
	 * @param themes themes attached to the case
	 */
	public void setThemes(Set<ITheme> themes) {
		this.themes = themes;
	}

	/**
	 * Set the offers of the case.
	 * @param offers offers attached to the case
	 */
	public void setOffers(Set<IOffer> offers) {
		this.offers = offers;
	}

	/**
	 * Set the grantings of the case.
	 * @param grantings grantings attached to the case
	 */
	public void setGrantings(Set<IGranting> grantings) {
		this.grantings = grantings;
	}

	/**
	 * Set the attachments of the case.
	 * @param attachments attachments attached to the case
	 */
	public void setAttachments(Set<IAttachment> attachments) {
		this.attachments = attachments;
	}
}