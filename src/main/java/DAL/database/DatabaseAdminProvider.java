package DAL.database;

import ACQ.IAccount;
import ACQ.IAdminService;
import ACQ.IUser;
import DAL.dataobject.Account;
import DAL.dataobject.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class DatabaseAdminProvider extends PostgreSqlDatabase implements IAdminService {
	@Override
	public boolean accountExists(String accountName) {
		AtomicBoolean exists = new AtomicBoolean(false);

		executeQuery(conn -> {
			PreparedStatement ps = conn.prepareStatement("SELECT id FROM accounts WHERE username = ?;");
			ps.setString(1, accountName);

			ResultSet rs = ps.executeQuery();

			exists.set(rs.next());
		});

		return exists.get();
	}

	@Override
	public boolean userExists(String ssn) {
		AtomicBoolean exists = new AtomicBoolean(false);

		executeQuery(conn -> {
			PreparedStatement ps = conn.prepareStatement("SELECT ssn FROM users WHERE ssn = ?;");
			ps.setString(1, ssn);

			exists.set(ps.execute());
		});

		return exists.get();
	}

	@Override
	public boolean lockAccount(String accountName) {
		AtomicBoolean locked = new AtomicBoolean(false);

		executeQuery(conn -> {
			PreparedStatement ps = conn.prepareStatement("UPDATE accounts SET islocked = true WHERE username = ? AND islocked = false;");
			ps.setString(1, accountName);

			locked.set(ps.executeUpdate() == 1);
		});

		return locked.get();
	}

	@Override
	public boolean unlockAccount(String accountName) {
		AtomicBoolean unlocked = new AtomicBoolean(false);

		executeQuery(conn -> {
			PreparedStatement ps = conn.prepareStatement("UPDATE accounts SET islocked = false WHERE username = ? AND islocked = true;");
			ps.setString(1, accountName);

			unlocked.set(ps.executeUpdate() == 1);
		});

		return unlocked.get();
	}

	@Override
	public boolean changeSecurityLevel(String accountName, int newSecurityLevel) {
		AtomicBoolean changed = new AtomicBoolean(false);

		executeQuery(conn -> {
			PreparedStatement ps = conn.prepareStatement("UPDATE accounts SET securitylevel = ? WHERE username = ? AND securitylevel != ?;");
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
			PreparedStatement ps = conn.prepareStatement("UPDATE accounts SET password_hash = ? WHERE username = ? AND password_hash != ?;");

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

			User data;
			while(rs.next()) {
				data = new User();
				DatabaseHelper.setUserFromResultSet(rs, data);
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

			Account data;
			while(rs.next()) {
				data = new Account();
				DatabaseHelper.setAccountFromResultSet(rs, data);
				accounts.add(data);
			}
		});

		return accounts;
	}
}