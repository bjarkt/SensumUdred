package UI.components.landing_page;

import UI.components.IComponent;
import UI.components.IEventListener;

public interface ILandingPage extends IComponent {


    void onLogIn(IEventListener<?> listener);

}
