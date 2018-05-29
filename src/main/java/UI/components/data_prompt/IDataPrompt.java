package UI.components.data_prompt;

import ACQ.IEventListener;
import UI.components.IComponent;

import java.util.List;

public interface IDataPrompt extends IComponent {

    void onContinue(IEventListener<List<String>> listener);

    void setPrompt(String prompt);

    void addTextFields(String ...field);

    void setButtonText(String text);

}
