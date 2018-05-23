package UI.components.elucidation_view;

import UI.components.IComponent;
import ACQ.IEventListener;

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
}
