package BLL.open_case;

import BLL.ACQ.ITheme;
import BLL.ACQ.IUser;
import BLL.theme_manager.Theme;
import javafx.util.Pair;

import java.util.Collection;
import java.util.Set;

public interface ICase {

    /**
     * Add one or more offers to the case.
     * @param offering One or more Pairs of offers and corresponding paragraphs.
     */
    void addOffers(Pair<String, String>...offering);

    /**
     * Add one or more grantings to the case..
     * @param granting  One ore more grantings.
     */
    void addGrantings(String ...granting);


    /**
     * Add one or more themes to the case
     * @param theme one or more themes.
     */
    void addThemes(ITheme...theme);

    /**
     * Get the themes for the case
     * @return themes
     */
    Set<ITheme> getThemes();

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
