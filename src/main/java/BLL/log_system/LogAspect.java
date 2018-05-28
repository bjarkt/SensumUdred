package BLL.log_system;

import ACQ.ILoggingService;
import ACQ.LogAction;
import ACQ.LogLevel;
import ACQ.Loggable;
import BLL.security_system.SecuritySystem;
import DAL.IPersistent;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

@Aspect
public class LogAspect {
	private static ILoggingService logService;

	/**
	 * Set the log service for the aspect.
	 * @param logService any log service
	 */
	public static void setLoggingService(ILoggingService logService) {
		LogAspect.logService = logService;
	}

	@After("@annotation(Loggable) && execution(* *(..))")
	public void logMethod(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();

		Loggable annotation = method.getAnnotation(Loggable.class);

		LogLevel ll = annotation.level();
		LogAction la = annotation.action();
		String desc = annotation.actionDescription();
		// int userSecurityLevel = SecuritySystem.getInstance().getAccount().getSecurityLevel();

		long id = logService.logEvent(method.getName(), desc, ll, la);
		logService.addToLogs(10002, -1, -1, id);
	}
}