package UI.components.elucidation_view.textfield_with_checkbox;

import ACQ.IEventListener;
import UI.components.IComponent;

public interface ITextFieldWithCheckbox extends IComponent {

    void onTextFieldSelected(IEventListener<ITextFieldWithCheckbox> listener);

    boolean isSelected();

}
