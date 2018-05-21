package ACQ;

import ACQ.IUser;

public interface ISigningService {

    IUser signIn(String username, String password);

    void signOut();

    void signUpAccount(String username, String password, int securityLevel);

    void signUpUser(String SSN, String firstName, String lastName, String phoneNumber, String username, String password);

    void signUpUser(String SSN);
}