package UI;

import ACQ.IUser;
import BLL.IBusiness;
import BLL.security_system.SecurityLevel;
import UI.components.IComponent;
import UI.components.popUp.IPopup;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

@Aspect
public aspect SecuredAspect {

    private static IUser user;
    private static IBusiness business;
    private static IPopup popup;

    /**
     *
     * @param user
     */
    public static void setUser(IUser user){
        SecuredAspect.user = user;
    }

    /**
     *
     * @param business
     */
    public static void setBusiness(IBusiness business){
        SecuredAspect.business = business;
    }

    /**
     *
     * @param popup
     */
    public static void setPopup(IPopup popup){
        SecuredAspect.popup = popup;
    }

    @Pointcut("get(* *.*)")
    public void getField(){

    }


    @Pointcut("@annotation(UI.Secured)")
    public void getSecuredAnnotation(){

    }

    @Before("getField() && getSecuredAnnotation()")
    public void hej3(JoinPoint joinPoint){
        try {
            Field f = joinPoint.getThis().getClass().getDeclaredField(joinPoint.getSignature().getName());
            String methodName = f.getAnnotation(Secured.class).value();

            if(user.getAccount().getSecurityLevel() < business.getClass().getMethod(methodName).getAnnotation(SecurityLevel.class).value()){
                f.setAccessible(true);
                Object o = f.get(joinPoint.getThis());
                if(o instanceof Node){
                    if(((Node) o).getParent() instanceof GridPane){
                        GridPane gridPane = (GridPane)((Node) o).getParent();
                        int rowIndex = gridPane.getRowIndex(((Node) o));
                        deleteRow(gridPane, rowIndex);
                    } else if(((Node) o).getParent() instanceof Pane){
                        ((Pane)((Node) o).getParent()).getChildren().remove(o);
                    }
                } /*if(o instanceof IComponent){
                    ((Pane)((IComponent) o).getView().getParent()).getChildren().remove(o);
                } */else{
                    popup.show("Access Denied", "You have no access");
                }
            }
        } catch (NoSuchFieldException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    /**
     * Deletes row and moves nodes.
     * @param row row to be deleted.
     */
    private void deleteRow(GridPane grid, int row){
        Set<Node> deleteNodes = new HashSet<>();
        for (Node child : grid.getChildren()) {
            Integer rowIndex = GridPane.getRowIndex(child);
            int r = rowIndex == null ? 0 : rowIndex;
            if (r > row) {
                GridPane.setRowIndex(child, r-1);
            } else if (r == row) {
                deleteNodes.add(child);
            }
        }
        grid.getChildren().removeAll(deleteNodes);
    }

}
