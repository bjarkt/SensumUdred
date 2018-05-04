package BLL.account_system;

import BLL.ACQ.IUser;

public interface ILoginService {

    IUser login(String username, String password);

}
