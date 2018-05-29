package UI.components.elucidation_view.granting;

import ACQ.IEventListener;
import UI.components.IComponent;

public interface IGrantingUI extends IComponent {

    void onGrantingSelected(IEventListener<IGrantingUI> listener);

    boolean isSelected();

    void setData(String description, String paragraph);

}
