package BLL.Inquiry;

import ACQ.IUser;
import ACQ.Task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class Inquiry extends Task implements IInquiry {

    private Set<IUser> attendee;
    private String CPR;
    private String name;
    private String addressCity;
    private String AddressStreet;
    private String gender;
    private String birthDate;
    private String civilStatus;
    private Date registrationDate;
    private String description;
    private String Offerings;
    private String inquirySource;
    private String Grantings;
    private String Guardianship;
    private String contactDetailsPhone;
    private String contactDetailsCell;
    private String CitizenAgreement;
    private String citizinMunicipality;
    private String specialCircumstances;


    @Override
    public String getAddress() {
        return null;
    }

    @Override
    public void setAddress(String address) {

    }

    public String getOfferings() {
        return Offerings;
    }

    public void setOfferings(String offerings) {
        Offerings = offerings;
    }

    public String getInquirySource() {
        return inquirySource;
    }

    public void setInquirySource(String inquirySource) {
        this.inquirySource = inquirySource;
    }

    public String getGrantings() {
        return Grantings;
    }

    public void setGrantings(String grantings) {
        Grantings = grantings;
    }

    public String getGuardianship() {
        return Guardianship;
    }

    public void setGuardianship(String guardianship) {
        Guardianship = guardianship;
    }


    public String getContactDetailsPhone() {
        return contactDetailsPhone;
    }

    public void setContactDetailsPhone(String contactDetailsPhone) {
        this.contactDetailsPhone = contactDetailsPhone;
    }

    public String getContactDetailsCell() {
        return contactDetailsCell;
    }

    public void setContactDetailsCell(String contactDetailsCell) {
        this.contactDetailsCell = contactDetailsCell;
    }

    public String getCitizenAgreement() {
        return CitizenAgreement;
    }

    public void setCitizenAgreement(String citizenAgreement) {
        CitizenAgreement = citizenAgreement;
    }

    public String getCitizinMunicipality() {
        return citizinMunicipality;
    }

    public void setCitizinMunicipality(String citizinMunicipality) {
        this.citizinMunicipality = citizinMunicipality;
    }

    public String getSpecialCircumstances() {
        return specialCircumstances;
    }

    public void setSpecialCircumstances(String specialCircumstances) {
        this.specialCircumstances = specialCircumstances;
    }


    public Inquiry() {
        attendee = new HashSet<>();
        registrationDate = new Date();
    }

    public Set<IUser> getAttendee() {
        return attendee;
    }


    public void addAttendee(IUser... attendee) {
        this.attendee.addAll(Arrays.asList(attendee));
    }

    public void removeAttendee(IUser... attendee) {
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

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressStreet() {
        return AddressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        AddressStreet = addressStreet;
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
        String CPRiD = sc.nextLine();
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
            ResultSet rs = st.executeQuery("SELECT CPR, name, addressStreet, addressCity, civilStatus, " +
                    "registrationDate, description, offerings, " +
                    "inquirySource, grantings, guardianship, contactDetailsPhone, contactDetailsCell, " +
                    " citizenAgreement, citizinMunicipality, specialCircumstances FROM people where name  = '" + CPRiD + "'");


            setCPR(rs.getString(1));
            setName(rs.getString(2));
            setAddressStreet(rs.getString(3));
            setAddressCity(rs.getString(4));
            setCivilStatus(rs.getString(6));
            setRegistrationDate(rs.getDate(7));
            setDescription(rs.getString(8));
            setOfferings(rs.getString(9));
            setInquirySource(rs.getString(10));
            setGrantings(rs.getString(11));
            setGuardianship(rs.getString(12));
            setContactDetailsPhone(rs.getString(13));
            setContactDetailsCell(rs.getString(14));
            setCitizenAgreement(rs.getString(15));
            setCitizinMunicipality(rs.getString(16));
            setSpecialCircumstances(rs.getString(17));


            rs.close();

            st.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void saveInquiry() {
        getInquiry();
        Scanner input = new Scanner(System.in);



        String url = "jdbc:postgresql://horton.elephantsql.com:5432/cxiasneu";
        String username = "cxiasneu";
        String password = "OY2shAU8fq2NQXMpbxU21AFNmOczgUkF";
        try {
            Connection db = DriverManager.getConnection(url, username, password);

            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("SELECT CPR, Name, Address, Gender, CivilStatus, " +
                    "RegistrationDate, inquiryDescription, Offerings, " +
                    "inquirySource, Grantings, Guardianship, contactDetails," +
                    " CitizenAgreement, citizinMunicipality, specialCircumstances FROM people where name  = '" + input + "'");

            rs.close();

            st.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        Scanner input2 = new Scanner(System.in);
        String word = input2.next();
        try {
            Connection db = DriverManager.getConnection(url, username, password);

            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("UPDATE CPR SET CPR = '" + word + "' WHERE name = '" + getCPR() + "'");

            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println(e);
        }


    }


}
