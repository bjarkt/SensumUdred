package BLL.security_system;

import BLL.ACQ.IUser;
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
	private static int userLevel;

	/**
	 * Sets the user for the security system.
	 * @param user any user
	 */
	static void setUserLevel(IUser user) {
		userLevel = user.getAccessLevel().ordinal();
	}

	/**
	 * A logic that runs every time a method with the {@link SecurityLevel} annotation.
	 * @param joinPoint a joinpoint
	 * @return the method
	 * @throws Throwable throws a {@link SecurityException} if user does not have access
	 */
	@Around("@annotation(SecurityLevel) && execution(* *(..))")
	public Object securityLogic(ProceedingJoinPoint joinPoint) throws Throwable {
		Object returnObject;

		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();

		SecurityLevel annotation = method.getAnnotation(SecurityLevel.class);

		int securityLevel = annotation.value();

		if(userLevel >= securityLevel) {
			returnObject = joinPoint.proceed();
		} else {
			throw new SecurityException("User does not have the privilege to this method.");
		}

		return returnObject;
	}
}