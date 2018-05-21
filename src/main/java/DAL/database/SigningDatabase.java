package DAL.database;

import ACQ.ISigningService;
import ACQ.IUser;

public class SigningDatabase extends PostgreSqlDatabase implements ISigningService {
	@Override
	public IUser signIn(String username, String password) {
		return null;
	}

	@Override
	public void signOut() {

	}

	@Override
	public void signUpAccount(String username, String password, int securityLevel) {

	}

	@Override
	public void signUpUser(String SSN, String firstName, String lastName, String phoneNumber, String username, String password) {

	}

	@Override
	public void signUpUser(String SSN) {

	}
}