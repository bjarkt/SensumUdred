package UI.components.elucidation_view;

import ACQ.*;
import UI.components.IComponent;
import UI.components.elucidation_view.theme.IThemeUI;

import java.util.Set;

public interface IElucidationView extends IComponent {

    void setElucidationData(IElucidation elucidation);

    // Event listeners

    void onLeaveElucidation(IEventListener<?> listener);

    void onCaseSaveDescription(IEventListener<String> listener);

    void onAddNewOffer(IEventListener<String> listener);

    void onDeleteOffers(IEventListener<String[]> listener);

    void tickOffersList(String ...offers);

    void onCreateMeeting(IEventListener<?> listener);

    void onCaseCitzenAgreement (IEventListener<String> listener);

    void onCaseCitizenMunicipality (IEventListener<String> listener);

    void onCaseSpecialCircumstancesField (IEventListener<String> listener);

    void onCaseCitizenInformation (IEventListener<String> listener);

    void onAddNewTheme(IEventListener<Set<IThemeUI>> listener);

    void onDeleteTheme(IEventListener<Set<IThemeUI>> listener);

    void onSendMessage(IEventListener<?> listener);

}
