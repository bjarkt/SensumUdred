package UI.components.elucidation_view;

import ACQ.*;
import UI.components.IComponent;
import UI.components.elucidation_view.theme.IThemeUI;

import java.util.Set;

public interface IElucidationView extends IComponent {

    void setElucidationData(IElucidation elucidation);

    /**
     *
     * @param required
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
    void onCaseCitizenInformation (IEventListener<String> listener);

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
    void onToggleState(IEventListener<ElucidationState> listener);

    void onCloseCase(IEventListener<?> listener);

}
