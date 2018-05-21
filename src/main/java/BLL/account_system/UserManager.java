package BLL.account_system;

import ACQ.ISigningService;
import ACQ.IUser;
import ACQ.IUserManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class UserManager implements IUserManager, ISigningService {
    private static UserManager INSTANCE;
    private User user;

    private UserManager(){

    }

    public static UserManager getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new UserManager();
        }

        return INSTANCE;
    }

    @Override
    public IUser signIn(String username, String password) {
        User user = null;

        try(Scanner scanner = new Scanner(new File("users.txt"))){
            String[] tokens;

            while(scanner.hasNextLine()){
                tokens = scanner.nextLine().split("\t");

                if(tokens[4].equals(username) && tokens[5].equals(password)){
                    Account account = new Account(username, 1000);
                    user = new User(account, "Firstname", "Lastname", "1234567890");
                    this.user = user;
                }
            }
        } catch (FileNotFoundException ex){
            ex.printStackTrace();
        }

        return user;
    }

    @Override
    public void signOut() {
        this.user = null;
    }

    @Override
    public void signUpAccount(String username, String password, int securityLevel) {

    }

    @Override
    public void signUpUser(String SSN, String firstName, String lastName, String phoneNumber, String username, String password) {
        try(PrintWriter outputStream = new PrintWriter(new FileOutputStream("users.txt",true))){
            outputStream.write(firstName+"\t"+lastName+"\t"+SSN+"\t"+phoneNumber+"\t"+username+"\t"+password+"\n");
        } catch (FileNotFoundException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void signUpUser(String SSN) {

    }

    @Override
    public boolean getSignedUser() {
        return false;
    }

    @Override
    public boolean deleteUser() {
        return false;
    }

    @Override
    public boolean deleteAccount() {
        return false;
    }

    @Override
    public boolean accountExists() {
        return false;
    }

    @Override
    public boolean userExists() {
        return false;
    }

    @Override
    public boolean changeSecurityLevel() {
        return false;
    }

    @Override
    public boolean changePassword() {
        return false;
    }

    @Override
    public boolean lockAccount() {
        return false;
    }

    @Override
    public boolean unlockAccount() {
        return false;
    }
}
