package DAL.database.providers;

import ACQ.IAdminService;
import DAL.database.PostgreSqlDatabase;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.PreparedStatement;
import java.util.concurrent.atomic.AtomicBoolean;

public class DatabaseAdminProvider extends PostgreSqlDatabase implements IAdminService {
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean changeSSN(String oldSSN, String newSSN) {
		return changeStringValueOnColumnForUser(oldSSN, "ssn", newSSN);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean changeFirstName(String ssn, String newFirstName) {
		return changeStringValueOnColumnForUser(ssn, "firstname", newFirstName);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean changeLastName(String ssn, String newLastName) {
		return changeStringValueOnColumnForUser(ssn, "lastname", newLastName);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean changePhoneNumber(String ssn, String newPhoneNumber) {
		return changeStringValueOnColumnForUser(ssn, "phonenumber", newPhoneNumber);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean changeEmail(String ssn, String newEmail) {
		return changeStringValueOnColumnForUser(ssn, "email", newEmail);
	}

	/**
	 * {@inheritDoc}
	 */
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

	/**
	 * {@inheritDoc}
	 */
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

	/**
	 * {@inheritDoc}
	 */
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

	/**
	 * {@inheritDoc}
	 */
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

	private boolean changeStringValueOnColumnForUser(String ssn, String column, String value) {
		AtomicBoolean bool = new AtomicBoolean(false);

		executeQuery(conn -> {
			PreparedStatement ps = conn.prepareStatement("UPDATE users SET ? = ? WHERE ssn = ?;");
			ps.setString(1, column);
			ps.setString(2, value);
			ps.setString(3, ssn);

			bool.set(ps.executeUpdate() == 1);
		});

		return bool.get();
	}
}