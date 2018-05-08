package BLL.security_system;

import BLL.ACQ.IUser;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

@Aspect
@SuppressAjWarnings("adviceDidNotMatch")
public class SecurityAspect {
	private static int userLevel;

	public static void setUserLevel(IUser user) {
		userLevel = user.getAccessLevel().ordinal();
	}

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
			throw new SecurityException("User does not have access to the method.");
		}

		return returnObject;
	}
}