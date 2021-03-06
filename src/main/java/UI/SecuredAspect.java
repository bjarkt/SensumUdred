package UI;

import ACQ.IAccount;
import ACQ.SecurityLevel;
import BLL.IBusiness;
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
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

@Aspect
public class SecuredAspect {

    private static IAccount account;
    private static IBusiness business;
    private static IPopup popup;

    /**
     *
     * @param account
     */
    public static void setAccount(IAccount account){
        SecuredAspect.account = account;
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
    public void removeSecuredComponents(JoinPoint joinPoint){
        try {
            Field f = joinPoint.getThis().getClass().getDeclaredField(joinPoint.getSignature().getName());
            String methodName = f.getAnnotation(Secured.class).value();
            int requiredSecurityLevel = -1;

            // Find method's security level.
            for (Method method : business.getClass().getMethods()) {
                if(method.getName().equals(methodName)) {
                    requiredSecurityLevel = method.getAnnotation(SecurityLevel.class).value();
                    break;
                }
            }


            if(account.getSecurityLevel() < requiredSecurityLevel){
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
                } else if(o instanceof IComponent){
                    if(((IComponent) o).getView().getParent() != null){
                        ((Pane)((IComponent) o).getView().getParent()).getChildren().remove(((IComponent) o).getView());
                    }
                } else{
                    popup.show("Access Denied", "You have no access");
                }
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
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
