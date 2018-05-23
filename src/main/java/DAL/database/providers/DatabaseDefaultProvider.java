package DAL.database.providers;

import ACQ.IAccount;
import ACQ.IDefaultService;
import ACQ.IUser;
import DAL.database.DatabaseHelper;
import DAL.database.PostgreSqlDatabase;
import DAL.dataobject.Account;
import DAL.dataobject.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class DatabaseDefaultProvider extends PostgreSqlDatabase implements IDefaultService {
	/**
	 * {@inheritDoc}
	 * Database service.
	 */
	@Override
	public IUser getUser(String ssn) {
		AtomicReference<User> user = new AtomicReference<>(new User());

		if(userExists(ssn)) {
			executeQuery(conn -> {
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE ssn = ?;");
				ps.setString(1, ssn);

				DatabaseHelper.setUserFromResultSet(ps.executeQuery(), user.get());
			});
		} else {
			user.set(null);
		}

		return user.get();
	}

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