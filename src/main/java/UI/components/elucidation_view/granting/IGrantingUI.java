package UI.components.elucidation_view.granting;

import UI.components.IComponent;
import ACQ.IEventListener;

public interface IGrantingUI extends IComponent {

    void onGrantingSelected(IEventListener<IGrantingUI> listener);

    boolean isSelected();

    void setData(String description, String paragraph);

}
