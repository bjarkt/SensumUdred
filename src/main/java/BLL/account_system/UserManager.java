package BLL.account_system;

import BLL.ACQ.IAddress;
import BLL.ACQ.IUser;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class UserManager {

    public UserManager(){

    }

    public boolean createUser(String firstName, String lastName, String ssn, String phoneNumber, String username, String password){

        try(PrintWriter outputStream = new PrintWriter(new FileOutputStream("users.txt",true))){
            outputStream.write(firstName+"\t"+lastName+"\t"+ssn+"\t"+"\t"+phoneNumber+"\t"+username+"\t"+password+"\n");
        } catch (FileNotFoundException ex){
            ex.printStackTrace();
        }

        return true;
    }


}
