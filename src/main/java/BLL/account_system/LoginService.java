package BLL.account_system;

import BLL.ACQ.IAddress;
import BLL.ACQ.IUser;
import BLL.ACQ.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
    public IUser login(String username, String password) {
        try(Scanner scanner = new Scanner(new File("users.txt"))){
            String[] tokens;
            while(scanner.hasNextLine()){
                tokens = scanner.nextLine().split("\t");
                if(tokens[4].equals(username) && tokens[5].equals(password)){
                    IUser user = new User(tokens[0], tokens[1], tokens[2]);
                    return user;
                }
            }
        } catch (FileNotFoundException ex){
            ex.printStackTrace();
        }
        return null;
    }
}
