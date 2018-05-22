package DAL.database;

import ACQ.IAccount;
import ACQ.IElucidation;
import ACQ.IMeeting;
import ACQ.IUser;
import DAL.dataobject.AccountData;
import DAL.dataobject.UserData;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class DatabaseService extends PostgreSqlDatabase implements IDatabaseService {
	@Override
	public boolean fileMeeting(IMeeting meeting) {
		final AtomicBoolean isFiled = new AtomicBoolean(false);
		final String query = "";

		executeQuery(conn-> {
			PreparedStatement ps = conn.prepareStatement(query);
			//ps.set
			// set the parameters to the SQL...

			isFiled.set(ps.executeUpdate() > 0);
		});

		return isFiled.get();
	}

	@Override
	public void testConnection() throws SQLException {
		PostgreSqlDatabase.getConnection();
	}

	@Override
	public IUser signIn(String username, String password) {
		UserData userData = new UserData();

		final String lowerUsername = username.toLowerCase(Locale.ROOT);

		executeQuery(conn -> {
			String query = "SELECT * FROM users NATURAL JOIN (SELECT users_ssn as ssn, password_hash, securitylevel, isloggedin, islocked FROM accounts JOIN haslogin ON accounts.username=?) as t;";

			PreparedStatement ps1 = conn.prepareStatement(query);
			ps1.setString(1, lowerUsername);

			ResultSet rs = ps1.executeQuery();

			if(rs.next() && !rs.getBoolean("isloggedin") && BCrypt.checkpw(password, rs.getString("password_hash"))) {
				AccountData account = new AccountData();
				account.setUsername(lowerUsername);
				account.setLocked(rs.getBoolean("islocked"));
				account.setSecurityLevel(rs.getInt("securitylevel"));

				 setUserDataFromResultSet(rs, userData, account);

				PreparedStatement ps2 = conn.prepareStatement("UPDATE accounts SET isloggedin=true, datelastlogin=? WHERE username=?;");
				ps2.setDate(1, new Date(System.currentTimeMillis()));
				ps2.setString(2, lowerUsername);

				ps2.execute();
			}
		});

		return userData;
	}

	@Override
	public boolean signOut(String accountName) {
		AtomicBoolean signedOut = new AtomicBoolean(false);

		executeQuery(conn -> {
			PreparedStatement ps = conn.prepareStatement("UPDATE accounts SET isloggedin=false WHERE username=? AND isloggedin=true;");
			ps.setString(1, accountName.toLowerCase(Locale.ROOT));

			signedOut.set(ps.executeUpdate() == 1);
		});

		return signedOut.get();
	}

	@Override
	public boolean signUpAccount(String ssn, String username, String password, int securityLevel) {
		AtomicBoolean signedUp = new AtomicBoolean(false);

		executeQuery(conn -> {
			PreparedStatement ps1 = conn.prepareStatement("INSERT INTO accounts(id, username, password_hash, securitylevel) VALUES (DEFAULT, ?, ?, ?);",
					Statement.RETURN_GENERATED_KEYS);

			ps1.setString(1, username.toLowerCase(Locale.ROOT));
			ps1.setString(2, BCrypt.hashpw(password, BCrypt.gensalt()));
			ps1.setInt(3, securityLevel);

			int updated = ps1.executeUpdate();
			ResultSet rs = ps1.getGeneratedKeys();

			if(updated == 1 && rs.next()) {
				long id = rs.getLong("id");

				PreparedStatement ps2 = conn.prepareStatement("INSERT INTO haslogin(users_ssn, accounts_id) VALUES (?, ?);");
				ps2.setString(1, ssn);
				ps2.setLong(2, id);

				signedUp.set(ps2.executeUpdate() == 1);
			}
		});

		return signedUp.get();
	}

	/**
	 * {@inheritDoc}
	 * IS NOT IMPLEMENTED
	 * @param SSN any ssn
	 * @return false
	 */
	@Override
	public boolean signUpUser(String SSN) {
		// TODO: Might not be available, since it requires a SNN Register to function.
		return false;
	}

	@Override
	public boolean signUpUser(String SSN, String firstName, String lastName, String phoneNumber, String email) {
		AtomicBoolean signedUp = new AtomicBoolean(false);

		executeQuery(conn -> {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO users VALUES (DEFAULT, ?, ?, ?, ?, ?);");

			ps.setString(1, SSN);
			ps.setString(2, firstName);
			ps.setString(3, lastName);
			ps.setString(4, phoneNumber);
			ps.setString(5, email);

			signedUp.set(ps.executeUpdate() == 1);
		});

		return signedUp.get();
	}

	@Override
	public boolean signUpUser(IUser user) {
		return signUpUser(user.getSocialSecurityNumber(), user.getFirstName(), user.getLastName(), user.getPhoneNumber(), user.getEmail());
	}

	@Override
	public boolean accountExists(String accountName) {
		AtomicBoolean exists = new AtomicBoolean(false);

		executeQuery(conn -> {
			PreparedStatement ps = conn.prepareStatement("SELECT id FROM accounts WHERE username=?;");
			ps.setString(1, accountName);

			exists.set(ps.execute());
		});

		return exists.get();
	}

	@Override
	public boolean userExists(String ssn) {
		AtomicBoolean exists = new AtomicBoolean(false);

		executeQuery(conn -> {
			PreparedStatement ps = conn.prepareStatement("SELECT ssn FROM users WHERE ssn=?;");
			ps.setString(1, ssn);

			exists.set(ps.execute());
		});

		return exists.get();
	}

	@Override
	public boolean lockAccount(String accountName) {
		AtomicBoolean locked = new AtomicBoolean(false);

		executeQuery(conn -> {
			PreparedStatement ps = conn.prepareStatement("UPDATE accounts SET locked=true WHERE username=? AND locked=false;");
			ps.setString(1, accountName);

			locked.set(ps.executeUpdate() == 1);
		});

		return locked.get();
	}

	@Override
	public boolean unlockAccount(String accountName) {
		AtomicBoolean unlocked = new AtomicBoolean(false);

		executeQuery(conn -> {
			PreparedStatement ps = conn.prepareStatement("UPDATE accounts SET locked=false WHERE username=? AND locked=true;");
			ps.setString(1, accountName);

			unlocked.set(ps.executeUpdate() == 1);
		});

		return unlocked.get();
	}

	@Override
	public boolean changeSecurityLevel(String accountName, int newSecurityLevel) {
		AtomicBoolean changed = new AtomicBoolean(false);

		executeQuery(conn -> {
			PreparedStatement ps = conn.prepareStatement("UPDATE accounts SET securitylevel=? WHERE username=? AND securitylevel != ?;");
			ps.setInt(1, newSecurityLevel);
			ps.setString(2, accountName);
			ps.setInt(3, newSecurityLevel);

			changed.set(ps.executeUpdate() == 1);
		});

		return changed.get();
	}

	@Override
	public boolean changePassword(String accountName, String newPassword) {
		AtomicBoolean changed = new AtomicBoolean(false);

		executeQuery(conn -> {
			PreparedStatement ps = conn.prepareStatement("UPDATE accounts SET password_hash=? WHERE username=? AND password_hash != ?;");

			String password_hash = BCrypt.hashpw(newPassword, BCrypt.gensalt(15));

			ps.setString(1, password_hash);
			ps.setString(2, accountName);
			ps.setString(3, password_hash);

			changed.set(ps.executeUpdate() == 1);
		});

		return changed.get();
	}

	@Override
	public Set<IUser> getAllUsers(int limit) {
		Set<IUser> users = new HashSet<>();

		executeQuery(conn -> {
			String query = "SELECT * FROM users" + (limit > 0 ? " LIMIT ?;" : ";");

			PreparedStatement ps = conn.prepareStatement(query);
			if(limit > 0) ps.setInt(1, limit);

			ResultSet rs = ps.executeQuery();

			UserData data;
			while(rs.next()) {
				data = new UserData();
				setUserDataFromResultSet(rs, data, null);
				users.add(data);
			}
		});

		return users;
	}

	@Override
	public Set<IAccount> getAllAccounts(int limit) {
		Set<IAccount> accounts = new HashSet<>();

		executeQuery(conn -> {
			String query = "SELECT * FROM accounts" + (limit > 0 ? " LIMIT ?;" : ";");

			PreparedStatement ps = conn.prepareStatement(query);
			if(limit > 0) ps.setInt(1, limit);

			ResultSet rs = ps.executeQuery();

			AccountData data;
			while(rs.next()) {
				data = new AccountData();
				setAccountDataFromResultSet(rs, data);
				accounts.add(data);
			}
		});

		return accounts;
	}

	@Override
	public boolean createElucidation(IElucidation elucidation) {
		return false;
	}

	@Override
	public boolean addCaseworkers(long id, IUser... users) {
		return false;
	}

	@Override
	public boolean removeCaseworkers(long id, IUser... users) {
		return false;
	}

	@Override
	public IElucidation getElucidation(long id) {
		return null;
	}

	private void setUserDataFromResultSet(ResultSet rs, UserData data, IAccount account) throws SQLException {

		data.setSsn(rs.getString("ssn"));
		data.setFirstName(rs.getString("ssn"));
		data.setLastName(rs.getString("ssn"));
		// TODO: Find and add the address to the user.
		data.setAddress(null);
		data.setPhoneNumber(rs.getString("ssn"));
		data.setEmail(rs.getString("ssn"));
		data.setAccount(account);
	}

	private void setAccountDataFromResultSet(ResultSet rs, AccountData data) throws SQLException {
		data.setUsername(rs.getString("username"));
		data.setLocked(rs.getBoolean("islocked"));
		data.setSecurityLevel(rs.getInt("securitylevel"));
	}
}