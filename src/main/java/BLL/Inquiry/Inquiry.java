package BLL.Inquiry;

import BLL.ACQ.IUser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;


public class Inquiry implements IInquiry {

    private Set<IUser> attendee;
    private String CPR;
    private String name;
    private String address;
    private String gender;
    private String birthDate;
    private String civilStatus;
    private Date registrationDate;
    private String description;

    public Inquiry() {
        attendee = new HashSet<>();
        registrationDate = new Date();
        }

    public Set<IUser> getAttendee() {
        return  attendee;
    }


    public void addAttendee (IUser ...attendee) {
        this.attendee.addAll(Arrays.asList(attendee));
    }

    public void removeAttendee(IUser ...attendee) {
        this.attendee.remove(attendee);
    }

    public String getCPR() {
        return CPR;
    }



    public void setCPR(String CPR) {
        this.CPR = CPR;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getCivilStatus() {
        return civilStatus;
    }

    public void setCivilStatus(String civilStatus) {
        this.civilStatus = civilStatus;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

private void saveInquiry(){
    File file = new File ("main/java/BLL/DATA/" + CPR + ".xml");

    try {


        PrintStream writer = new PrintStream(file);
        writer.println(CPR + "\n");
        writer.println(name + "\n");
        writer.println(address + "\n");
        writer.println(description + "\n");
        writer.println(gender + "\n");
        writer.println(birthDate + "\n");
        writer.println(civilStatus + "\n");


    } catch (FileNotFoundException ex) {
        System.out.println("RIP");
    }
}
private void editInquiry(){


    File file = new File ("main/java/BLL/DATA/" + CPR + ".txt");

    try {
        Scanner scanner = new Scanner(file);
        


    } catch (FileNotFoundException exception) {
        System.out.println("RIP");
    }

    }

}
