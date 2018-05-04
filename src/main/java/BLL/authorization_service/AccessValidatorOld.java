package BLL.authorization_service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD) // Use on method only
public @interface AccessValidatorOld {

    public boolean hasAccess();

}
