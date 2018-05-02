package BLL.open_case;

import BLL.ACQ.IUser;

import java.util.Set;

public interface ICase {
    /**
     * Adds description to citizens case.
     * @param description   description of case.
     */
    void addDescription(String description);

    /**
     * Accessor method for description.
     * @return  String  description of case
     */
    String getDescription();

    /**
     * Adds an object of type {@link IOffer}.
     * @param offer the offer to be added.
     */
    void addOffer(IOffer offer);


    /**
     * Accessor method for offers on the case.
     * @return  Set a set of offers.
     */
    Set<IOffer> getOffers();

    /**
     * Adds an object of type {@link IGranting}.
     * @param granting  the granting to be added.
     */
    void addGranting(IGranting granting);

    /**
     * Accessor method for grantings on the case.
     * @return  Set a set of grantings.
     */
    Set<IGranting> getGrantings();

    /**
     * Declares which user reported the case.
     * @param reporter  the user who reported the case.
     */
    void addReporter(IUser reporter);

    /**
     * Accessor method for reporter.
     * @return  IUser   the reporter.
     */
    IUser getReporter();

    /**
     * Declares the guardianship in this case.
     * @param guardian  the guardian.
     * @param guardianship  current guardianship.
     * @param informationOnAuthority    description about what authority has been given.
     */
    void setGuardianship(IUser guardian, Guardianship guardianship, String informationOnAuthority);

    /**
     * Sets citizen's consent to case opening.
     * @param citizenAgrees boolean value of citizen's consent.
     */
    void setConsentToCaseOpening(boolean citizenAgrees);

    /**
     * Sets the citisen's municipality
     * @param municipality  citisen's municipality
     */
    void setMunicipality(String municipality);

    /**
     * Sets any special circumstances to be noted.
     * @param specialCircumstances  description of special circumstances.
     */
    void addSpecialCircumstances(String specialCircumstances);

    /**
     * Saves the case.
     * @return  boolean confirmation of success or failure.
     */
    boolean saveCase();
}
