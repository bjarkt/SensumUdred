package BLL.security_system;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

@Aspect
public class SecurityAspect {

	@Around("@annotation(SecurityLevel) && execution(* *(..))")
	public Object securityLogic(ProceedingJoinPoint joinPoint) throws Throwable {
		//Default Object that we can use to return to the consumer
		Object returnObject;

		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();

		SecurityLevel annotation = method.getAnnotation(SecurityLevel.class);

		int securityLevel = annotation.value();

		System.out.println(securityLevel);

		if(10 >= securityLevel) {
			System.out.println("User has access to the method.");

			returnObject = joinPoint.proceed();
		} else {

			throw new SecurityException("User does not have access to the method.");
		}

		return returnObject;
	}
}
