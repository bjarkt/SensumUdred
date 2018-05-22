package BLL.security_system;

import ACQ.IAccount;
import ACQ.IEventListener;
import ACQ.IUser;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
/**
 * Contains the logic for the security system.
 * It is an Aspect from the AspectJ library.
 */
@Aspect
@SuppressAjWarnings("adviceDidNotMatch")
public class SecurityAspect {
	/**
	 * A logic that runs every time a method with the {@link SecurityLevel} annotation.
	 * @param joinPoint a joinpoint
	 * @return the method
	 * @throws Throwable throws a {@link SecurityException} if user does not have access
	 */
	@Around("@annotation(SecurityLevel) && execution(* *(..))")
	public Object securityLogic(ProceedingJoinPoint joinPoint) throws Throwable {
		Object returnObject = null;

		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();

		SecurityLevel annotation = method.getAnnotation(SecurityLevel.class);

		int securityLevel = annotation.value();

		IAccount account = SecuritySystem.getInstance().getAccount();

		if(account != null && account.getSecurityLevel() >= securityLevel) {
			returnObject = joinPoint.proceed();
		} else {
			SecurityException se = new SecurityException("User does not have the privilege to this method.");
			SecuritySystem.getInstance().getEventListener().onAction(se);
		}

		return returnObject;
	}
}