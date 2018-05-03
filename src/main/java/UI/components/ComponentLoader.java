package UI.components;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import java.util.ArrayList;

public final class ComponentLoader {

    private ComponentLoader() {
    }

    /**
     * Removes node or component from the scene graph with fading animation.
     * @param component node to be removed.
     */
    public static void removeComponent(Component component) {
        /*
        Timeline timeline = new Timeline();
        ArrayList<KeyFrame> keyFrames = new ArrayList<>();
        keyFrames.add(new KeyFrame(Duration.millis(200), new KeyValue(component.getParent().opacityProperty(), 0, Interpolator.EASE_BOTH)));
        timeline.getKeyFrames().addAll(keyFrames);
        timeline.play();
        timeline.setOnFinished(event1 -> {
            if (component.getParent() instanceof Pane) {
                ((Pane) component.getParent().getParent()).getChildren().remove(component.getParent());
            }
        });*/
        ((Pane) component.getParent().getParent()).getChildren().remove(component.getParent());
    }

    

}
