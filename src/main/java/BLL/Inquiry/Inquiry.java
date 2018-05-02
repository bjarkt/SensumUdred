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







private void saveInquiry(){
    File file = new File ("main/java/BLL/DATA/" + CPR + ".txt");

    try {
        Scanner scanner = new Scanner(file);

        PrintStream writer = new PrintStream(file);
        writer.println(CPR);
        writer.println(name);
        writer.println(address);
        writer.println(description);
        writer.println(gender);
        writer.println(birthDate);
        writer.println(civilStatus);


    } catch (FileNotFoundException ex) {
        System.out.println("RIP");
    }
}
}
