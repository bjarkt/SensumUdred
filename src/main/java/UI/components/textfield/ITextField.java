package UI.components.textfield;

import UI.components.IComponent;
import ACQ.IEventListener;
import UI.components.elucidation_view.granting.IGranting;

public interface ITextField extends IComponent {

    /**
     *
     * @return
     */
    String getText();

    /**
     *
     * @param prompt
     */
    void setPromptText(String prompt);

}
