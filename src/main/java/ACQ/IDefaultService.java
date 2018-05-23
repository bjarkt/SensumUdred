package ACQ;

import java.util.Set;

public interface IDefaultService {
	/**
	 * Get a user from the ssn.
	 * @param ssn any ssn
	 * @return a new object with all their information
	 */
	IUser getUser(String ssn);

	/**
	 * Returns a true or false, depending on an account exists.
	 * @param accountName any account name
	 * @return true, if accounts exists; otherwise false
	 */
	boolean accountExists(String accountName);

	/**
	 * Returns a true or false, depending on an user exists.
	 * @param ssn any ssn
	 * @return true, if user exists; otherwise false
	 */
	boolean userExists(String ssn);

	/**
	 * Get a set containing all users.
	 * Limit the amount with the parameter; 0 or negative for no limit.
	 * @return all users
	 */
	Set<IUser> getAllUsers(int limit);

	/**
	 * Get a set containing all accounts.
	 * Limit the amount with the parameter; 0 or negative for no limit.
	 * @param limit any number
	 * @return all accounts
	 */
	Set<IAccount> getAllAccounts(int limit);
}
