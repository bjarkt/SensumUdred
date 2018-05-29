package UI.components.elucidation_view;

import ACQ.ElucidationTaskState;
import ACQ.IElucidation;
import ACQ.IEventListener;
import UI.components.IComponent;
import UI.components.elucidation_view.theme.IThemeUI;

import java.util.Set;

public interface IElucidationView extends IComponent {

    /**
     * Updates the
     */
    void tick();

    /**
     * Sets which object of type {@link IElucidation} to be presented.
     * @param required  elucidation data to be shown.
     */
    void setRequired(IElucidationViewRequire required);

    // Event listeners

    /**
     *
     * @param listener
     */
    void onLeaveElucidation(IEventListener<?> listener);

    /**
     *
     * @param listener
     */
    void onCaseSaveDescription(IEventListener<String> listener);

    /**
     *
     * @param listener
     */
    void onAddNewOffer(IEventListener<String> listener);

    /**
     *
     * @param listener
     */
    void onDeleteOffers(IEventListener<String[]> listener);

    /**
     *
     * @param offers
     */
    void tickOffersList(String ...offers);

    /**
     *
     * @param listener
     */
    void onCreateMeeting(IEventListener<?> listener);

    /**
     *
     * @param listener
     */
    void onCaseCitzenAgreement (IEventListener<String> listener);

    /**
     *
     * @param listener
     */
    void onCaseCitizenMunicipality (IEventListener<String> listener);

    /**
     *
     * @param listener
     */
    void onCaseSpecialCircumstancesField (IEventListener<String> listener);

    /**
     *
     * @param listener
     */
    void onCaseCitizenInformation (IEventListener<String[]> listener);

    /**
     *
     * @param listener
     */
    void onAddNewTheme(IEventListener<Set<IThemeUI>> listener);

    /**
     *
     * @param listener
     */
    void onDeleteTheme(IEventListener<Set<IThemeUI>> listener);

    /**
     *
     * @param listener
     */
    void onSendMessage(IEventListener<?> listener);

    /**
     *
     * @param listener
     */
    void onToggleState(IEventListener<ElucidationTaskState> listener);

    void onCloseCase(IEventListener<?> listener);

}
