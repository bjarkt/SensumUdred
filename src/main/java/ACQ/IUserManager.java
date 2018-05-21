package ACQ;

public interface IUserManager {
	boolean getSignedUser();
	boolean deleteUser();
	boolean deleteAccount();
	boolean accountExists();
	boolean userExists();
	boolean changeSecurityLevel();
	boolean changePassword();
	boolean lockAccount();
	boolean unlockAccount();
}
