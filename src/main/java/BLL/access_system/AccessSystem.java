package BLL.access_system;

import BLL.ACQ.IUser;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public final class AccessSystem implements IAccessHandler {
	private static AccessSystem INSTANCE;
	private IUser user;
	private Set<Method> methods;

	private AccessSystem() {
		methods = new HashSet<>();
	}

	public static AccessSystem getINSTANCE() {
		if(INSTANCE == null) INSTANCE = new AccessSystem();

		return INSTANCE;
	}

	@Override
	public void setUser(IUser user) {
		this.user = user;
	}

	@Override
	public boolean init() {
		boolean initalized = false;

		if(user != null) {
			// Observe every method containing the AccessLevel Annotation.


			initalized = true;
		}

		return initalized;
	}
}