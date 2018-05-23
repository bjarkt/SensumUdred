package DAL.database;

import DAL.dataobject.Account;
import DAL.dataobject.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class DatabaseHelper {
	/**
	 * Takes all the data from a live result set into an account object.
	 * @param rs a result set with columns: ssn, firstname, lastname, phonenumber and email
	 * @param data user object to inject data
	 * @throws SQLException if the ResultSet is not alive anymore
	 */
	public static void setUserFromResultSet(ResultSet rs, User data) throws SQLException {
		data.setSsn(rs.getString("ssn"));
		data.setFirstName(rs.getString("firstname"));
		data.setLastName(rs.getString("lastname"));
		// TODO: Find and add the address to the user.
		data.setAddress(null);
		data.setPhoneNumber(rs.getString("phonenumber"));
		data.setEmail(rs.getString("email"));
	}

	/**
	 * Takes all the data from a live result set into an account object.
	 * @param rs a result set with columns: username, islocked and securitylevel
	 * @param data account object to inject data
	 * @throws SQLException if the ResultSet is not alive anymore
	 */
	public static void setAccountFromResultSet(ResultSet rs, Account data) throws SQLException {
		data.setUsername(rs.getString("username"));
		data.setLocked(rs.getBoolean("islocked"));
		data.setSecurityLevel(rs.getInt("securitylevel"));
	}
}
