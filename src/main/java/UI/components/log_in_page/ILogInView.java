package UI.components.log_in_page;

import UI.components.IComponent;
import UI.components.IEventListener;

public interface ILogInView extends IComponent {


    void onLogIn(IEventListener<String[]> listener);

}
