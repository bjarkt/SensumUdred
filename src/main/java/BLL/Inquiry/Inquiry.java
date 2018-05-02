package BLL.Inquiry;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Inquiry {

    private String CPR;
    private String name;
    private String address;
    private String description;
    private String gender;
    private String birthDate;
    private String civilStatus;







private void editInquiry(){
    File file = new File ("main/java/BLL/DATA/");

    try {
        Scanner scanner = new Scanner(file);

        PrintStream writer = new PrintStream(file);



    } catch (FileNotFoundException ex) {
        System.out.println("RIP");
    }
}
}
