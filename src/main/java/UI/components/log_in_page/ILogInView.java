package UI.components.log_in_page;

import UI.components.IComponent;
import ACQ.IEventListener;

public interface ILogInView extends IComponent {


    void onLogIn(IEventListener<String[]> listener);

    void writeError(String message);

}
