package UI.components.meetings_view;

import ACQ.IDialog;
import ACQ.IElucidation;
import ACQ.IEventListener;
import UI.components.IComponent;

import java.util.Set;

public interface IMeetingsView extends IComponent {
    void tickList(Set<IDialog> dialog);

    void disableList();

    void enableList();

}
