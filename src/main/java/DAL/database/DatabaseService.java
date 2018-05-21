package DAL.database;

import ACQ.IAccount;
import ACQ.IMeeting;
import ACQ.IUser;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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
		return null;
	}

	@Override
	public boolean signOut() {
		return false;
	}

	@Override
	public boolean signUpAccount(String username, String password, int securityLevel) {
		AtomicBoolean signedUp = new AtomicBoolean(false);

		executeQuery(conn -> {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO accounts VALUES (?, ?, ?, ?);");

			ps.setString(1, username);
			ps.setString(2, password);
			ps.setInt(3, securityLevel);

			signedUp.set(ps.executeUpdate() == 1);
		});

		return signedUp.get();
	}

	@Override
	public boolean signUpUser(String SSN) {
		return false;
	}

	@Override
	public boolean signUpUser(String SSN, String firstName, String lastName, String phoneNumber, String email) {
		AtomicBoolean signedUp = new AtomicBoolean(false);

		executeQuery(conn -> {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO users VALUES (?, ?, ?, ?, ?);");

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
		return false;
	}

	@Override
	public boolean accountExists(IAccount account) {
		return false;
	}

	@Override
	public boolean userExists(IUser user) {
		return false;
	}

	@Override
	public boolean lockAccount(IAccount account) {
		return false;
	}

	@Override
	public boolean unlockAccount(IAccount account) {
		return false;
	}

	@Override
	public boolean changeSecurityLevel(IAccount account, int newSecurityLevel) {
		return false;
	}

	@Override
	public boolean changePassword(IAccount account, String newPassword) {
		return false;
	}
}