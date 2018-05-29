package UI.components.log_in_page;

import ACQ.IEventListener;
import UI.components.IComponent;

public interface ILogInView extends IComponent {


    void onLogIn(IEventListener<String[]> listener);

    void writeError(String message);

}
