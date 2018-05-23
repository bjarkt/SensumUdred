package UI.components.data_prompt;

import UI.components.IComponent;
import ACQ.IEventListener;
import UI.components.textfield.ITextField;

import java.util.List;

public interface IDataPrompt extends IComponent {

    void onContinue(IEventListener<List<String>> listener);

    void setPrompt(String prompt);

    void addTextFields(String ...field);

}
