package UI.components.elucidation_view.granting;

import UI.components.IComponent;
import ACQ.IEventListener;

public interface IGranting extends IComponent {

    void onGrantingSelected(IEventListener<IGranting> listener);

    boolean isSelected();

}
