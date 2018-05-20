package BLL.redirect_citizens_to_other_department;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class RedirectCitizen {

    private boolean Approved;
    private String  CPR;


            public static void main(String args[]) {

                String fileName = "main/java/DATA/\" + test + \".txt";

                try (Scanner scanner = new Scanner(new File(fileName))) {

                    while (scanner.hasNext()){
                        System.out.println(scanner.nextLine());
                    }

                } catch (IOException e) {
                    System.out.println("file not found");
                    e.printStackTrace();
                    }
        }

    }

