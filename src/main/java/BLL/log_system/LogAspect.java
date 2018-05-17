package BLL.log_system;

import BLL.security_system.SecuritySystem;
import DAL.IPersistent;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

@Aspect
public class LogAspect {
	private static IPersistent persistent;

	public static void setPersistent(IPersistent persistent) {
		LogAspect.persistent = persistent;
	}

	@After("@annotation(Loggable) && execution(* *(..))")
	public void logMethod(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();

		Loggable annotation = method.getAnnotation(Loggable.class);

		LogLevel ll = annotation.level();
		LogAction la = annotation.action();
		String desc = annotation.actionDescription();
		int userSecurityLevel = SecuritySystem.getInstance().getUser().getAccessLevel();

		System.out.println(ll);
		System.out.println(la);
		System.out.println(desc);
		System.out.println(userSecurityLevel);
	}
}