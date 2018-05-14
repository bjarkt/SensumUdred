package BLL.ACQ;

import BLL.open_case.Guardianship;
import com.sun.istack.internal.Nullable;
import javafx.util.Pair;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface IElucidation {

    /**
     * Get the applicant or citizen this elucidation involves.
     */
    IUser getCitizen();

    /**
     * Add one or more caseworkers to the elucidation.
     * @param caseworker    one or more caseworkers.
     */
    void addCaseworker(IUser ...caseworker);

    /**
     * Get the caseworkers on this elucidation.
     * @return  the caseworkers on this elucidation.
     */
    Set<IUser> getCaseworkers();

    /**
     * Opens case based on the existing inquiry.
     * @param description   What the case is about.
     * @param offerings Pairs of offers and corresponding paragraphs.
     * @param grantings Any grantings given.
     * @param guardian  Citizens guardian.
     * @param guardianship  Type of guardianship.
     * @param citizenAgreesWithOpeningOfCase    True of citizen has agreed with opening of the case.
     * @param citizensMunicipality  Municipality of the citizen.
     * @param specialCircumstances  Any special circumstances noted by the creator.
     */
    void openCase(String description, @Nullable List<Pair<String, String>> offerings, @Nullable List<String> grantings, @Nullable IUser guardian, @Nullable Guardianship guardianship, String informationAboutAuthority, boolean citizenAgreesWithOpeningOfCase, String citizensMunicipality, String specialCircumstances);

    /**
     * Get the time of opening the elucidation.
     * @return  time of opening.
     */
    Date getTimeOfOpening();

}
