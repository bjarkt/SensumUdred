package UI.components.elucidation_view;

import UI.components.IComponent;
import ACQ.IEventListener;

public interface IElucidationView extends IComponent {

    void onLeaveElucidation(IEventListener<?> listener);

    void onCaseSaveDescription(IEventListener<String> listener);

    void onAddNewOffer(IEventListener<String> listener);

    void onDeleteOffers(IEventListener<String[]> listener);

    void tickOffersList(String ...offers);

}
