package UI.components;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public abstract class Component {

    /** Reference to the component root. */
    private Parent parent;
    /** Reference to the FXML path. */
    private String resource;

    /** Name of what this component represents, most useful for whole page views. */
    private String breadcrumb;

    /**
     * Constructor.
     * @param resource  path to the FXML file.
     */
    public Component(String resource) {
        this.resource = resource;
    }

    public Component(String resource, String breadcrumb) {
        this.resource = resource;
        this.breadcrumb = breadcrumb;
    }


    /**
     * Returns an object of type {@link Parent} that can be added to the scene graph.
     * @return  the loaded object hierarchy.
     */
    public Parent getView(){
        if(parent != null) return parent;
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
            loader.setControllerFactory(param -> this);
            return (parent = (loader.load())); // return object hierarchy.
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Accessor method for breadcrumb attribute.
     * @return  the name of the component.
     */
    public StringProperty getBreadcrumb() {
        StringProperty breadcrumb = new SimpleStringProperty(this.breadcrumb);
        return breadcrumb;
    }

    /**
     * Accessor method for the component root.
     * @return  the root of the component.
     */
    public Parent getParent() {
        return parent;
    }

    /**
     * Mutator method for the component root.
     * @param parent    the root fo the element.
     */
    public void setParent(Parent parent) {
        this.parent = parent;
    }
}
