package UI.components.log_view;

import ACQ.*;
import UI.components.IComponent;

import java.util.Set;

public interface ILogsView extends IComponent {
    void tickList(Set<IEventLog> dialog);

    void disableList();

    void enableList();

}
