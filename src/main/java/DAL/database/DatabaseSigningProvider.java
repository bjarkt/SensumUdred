package DAL.database;

import ACQ.IProfile;
import ACQ.ISigningService;
import ACQ.IUser;
import DAL.dataobject.Account;
import DAL.dataobject.Profile;
import DAL.dataobject.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

public class DatabaseSigningProvider extends PostgreSqlDatabase implements ISigningService {
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IProfile signIn(String username, String password) {
		Profile profile = new Profile();

		final String lowerUsername = username.toLowerCase(Locale.ROOT);

		executeQuery(conn -> {
			String query = "SELECT users.*, accounts.* FROM users, haslogin, accounts WHERE users.ssn = haslogin.users_ssn AND haslogin.accounts_id = accounts.id AND accounts.username = ?;";

			PreparedStatement ps1 = conn.prepareStatement(query);
			ps1.setString(1, lowerUsername);

			ResultSet rs = ps1.executeQuery();

			if(rs.next() && !rs.getBoolean("isloggedin") && BCrypt.checkpw(password, rs.getString("password_hash"))) {
				Account account = new Account();
				DatabaseHelper.setAccountFromResultSet(rs, account);

				User user = new User();
				DatabaseHelper.setUserFromResultSet(rs, user);

				profile.setAccount(account);
				profile.setUser(user);

				PreparedStatement ps2 = conn.prepareStatement("UPDATE accounts SET isloggedin=true, datelastlogin=? WHERE username=?;");
				ps2.setDate(1, new Date(System.currentTimeMillis()));
				ps2.setString(2, lowerUsername);

				ps2.execute();
			}
		});

		return profile;
	}

	/**
	 * {@inheritDoc}
	 */
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

	/**
	 * {@inheritDoc}
	 */
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

	/**
	 * {@inheritDoc}
	 */
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean signUpUser(IUser user) {
		return signUpUser(user.getSocialSecurityNumber(), user.getFirstName(), user.getLastName(), user.getPhoneNumber(), user.getEmail());
	}
}