package UI.components.elucidation_view;

import ACQ.IEventListener;
import UI.components.IComponent;
import UI.components.elucidation_view.theme.ThemeData;

import java.util.Set;

public interface IElucidationView extends IComponent {

    void onLeaveElucidation(IEventListener<?> listener);

    void onCaseSaveDescription(IEventListener<String> listener);

    void onAddNewOffer(IEventListener<String> listener);

    void onDeleteOffers(IEventListener<String[]> listener);

    void tickOffersList(String ...offers);

    void onCaseCitzenAgreement (IEventListener<String> listener);

    void onCaseCitizenMunicipality (IEventListener<String> listener);

    void onCaseSpecialCircumstancesField (IEventListener<String> listener);

    void onCaseCitizenInformation (IEventListener<String> listener);

    void onAddNewTheme(IEventListener<Set<ThemeData>> listener);

    void onDeleteTheme(IEventListener<Set<ThemeData>> listener);

}
