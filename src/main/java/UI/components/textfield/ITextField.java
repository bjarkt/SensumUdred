package UI.components.textfield;

import UI.components.IComponent;

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
