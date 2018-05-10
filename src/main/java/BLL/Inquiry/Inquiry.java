package BLL.Inquiry;

import BLL.ACQ.IUser;
import BLL.ACQ.Task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;


public class Inquiry extends Task implements IInquiry {

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

    private void getInquiry() {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        String input1 = sc.nextLine();
        try {
            Class.forName("org.postgresql.Driver");
        } catch (java.lang.ClassNotFoundException e) {
            System.out.println(e);
        }

        String url = "jdbc:postgresql://horton.elephantsql.com:5432/cxiasneu";
        String username = "cxiasneu";
        String password = "OY2shAU8fq2NQXMpbxU21AFNmOczgUkF";

        try {
            Connection db = DriverManager.getConnection(url, username, password);

            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("SELECT CPR, FullName, Address, Gender, CivilStatus, " +
                    "RegistrationDate, inquiryDescription, Offerings, " +
                    "inquirySource, Grantings, Guardianship, contactDetails," +
                    " CitizenAgreement, citizinMunicipality, specialCircumstances FROM people where name  = '" + input1 + "'");



            while (rs.next()) {

                String CPR = rs.getString(1);
                String FullName = rs.getString(2);
                String Address = rs.getString(3);
                String Gender = rs.getString(4);
                String CivilStatus = rs.getString(5);
                String RegistrationDate = rs.getString(6);
                String inquiryDescription = rs.getString(7);
                String Offerings = rs.getString(8);
                String inquirySource = rs.getString(9);
                String Grantings = rs.getString(10);
                String Guardianship = rs.getString(11);
                String contactDetails = rs.getString(12);
                String CitizenAgreement = rs.getString(13);
                String citizinMunicipality = rs.getString(14);
                String specialCircumstances = rs.getString(15);



            }

            rs.close();

            st.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
