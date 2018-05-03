package BLL.account_system;

public class LoginService implements ILoginService {

    /*
    NOTE: Database should probably have:
    * id
    * username
    * password
    * failed attempts (reset on success)
    * last failed attempt
    * last successfull login
     */

    public LoginService(){}

    @Override
    public boolean login(String username, String password) {
        boolean isValidCredentials = true;
        return isValidCredentials;
    }
}
