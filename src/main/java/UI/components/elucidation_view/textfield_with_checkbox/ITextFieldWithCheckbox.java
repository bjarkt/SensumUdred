package UI.components.elucidation_view.textfield_with_checkbox;

import UI.components.IComponent;
import ACQ.IEventListener;
import UI.components.elucidation_view.granting.IGranting;

public interface ITextFieldWithCheckbox extends IComponent {

    void onGrantingSelected(IEventListener<IGranting> listener);

    boolean isSelected();

}
