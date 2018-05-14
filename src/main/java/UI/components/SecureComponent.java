package UI.components;

import com.jfoenix.controls.JFXButton;
import javafx.scene.Node;

public class SecureComponent extends JFXButton {

    private int securityLevel;

    private Node component;

    public SecureComponent(Node component, int securityLevel){
        super();
        this.component = component;
        this.securityLevel = securityLevel;
    }

    public Node getComponent() {
        return component;
    }

    public int getSecurityLevel() {
        return securityLevel;
    }


}
