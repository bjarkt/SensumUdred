package BLL.Inquiry;

import ACQ.IUser;
import ACQ.Task;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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
    private String Offerings;
    private String inquirySource;
    private String Grantings;
    private String Guardianship;
    private String contactDetails;
    private String CitizenAgreement;
    private String citizinMunicipality;
    private String specialCircumstances;



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

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
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
            ResultSet rs = st.executeQuery("SELECT CPR, name, address, gender, civilStatus, " +
                    "registrationDate, description, offerings, " +
                    "inquirySource, grantings, guardianship, contactDetails," +
                    " citizenAgreement, citizinMunicipality, specialCircumstances FROM people where name  = '" + CPRiD + "'");


                setCPR(rs.getString(1));
                setName(rs.getString(2));
                setAddress(rs.getString(3));
                setGender(rs.getString(4));
                setCivilStatus(rs.getString(5));
                setRegistrationDate(rs.getDate(6));
                setDescription(rs.getString(7));
                setOfferings(rs.getString(8));
                setInquirySource(rs.getString(9));
                setGrantings(rs.getString(10));
                setGuardianship(rs.getString(11));
                setContactDetails(rs.getString(12));
                setCitizenAgreement(rs.getString(13));
                setCitizinMunicipality(rs.getString(14));
                setSpecialCircumstances(rs.getString(14));






            rs.close();

            st.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }
    public void editInquiry(){
        getInquiry();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

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
        switch (word) {
            case "CPR":
                try {
                    Connection db = DriverManager.getConnection(url, username, password);

                    Statement st = db.createStatement();
                    ResultSet rs = st.executeQuery("UPDATE CPR SET CPR = '"+ word +"' WHERE name = '"+ getCPR() +"'");

                    rs.close();
                    st.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case "name":
                try {
                    Connection db = DriverManager.getConnection(url, username, password);

                    Statement st = db.createStatement();
                    ResultSet rs = st.executeQuery("UPDATE CPR SET CPR = '"+ word +"' WHERE name = '"+ getName() +"'");

                    rs.close();
                    st.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case "address":
                try {
                    Connection db = DriverManager.getConnection(url, username, password);

                    Statement st = db.createStatement();
                    ResultSet rs = st.executeQuery("UPDATE CPR SET CPR = '"+ word +"' WHERE name = '"+ getAddress() +"'");

                    rs.close();
                    st.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case "gender":
                try {
                    Connection db = DriverManager.getConnection(url, username, password);

                    Statement st = db.createStatement();
                    ResultSet rs = st.executeQuery("UPDATE CPR SET CPR = '"+ word +"' WHERE name = '"+ getGender() +"'");

                    rs.close();
                    st.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case "civilStatus":
                try {
                    Connection db = DriverManager.getConnection(url, username, password);

                    Statement st = db.createStatement();
                    ResultSet rs = st.executeQuery("UPDATE CPR SET CPR = '"+ word +"' WHERE name = '"+ getCivilStatus() +"'");

                    rs.close();
                    st.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case "registrationDate":
                try {
                    Connection db = DriverManager.getConnection(url, username, password);

                    Statement st = db.createStatement();
                    ResultSet rs = st.executeQuery("UPDATE CPR SET CPR = '"+ word +"' WHERE name = '"+ getRegistrationDate() +"'");

                    rs.close();
                    st.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case "description":
                try {
                    Connection db = DriverManager.getConnection(url, username, password);

                    Statement st = db.createStatement();
                    ResultSet rs = st.executeQuery("UPDATE CPR SET CPR = '"+ input +"' WHERE name = '"+ getDescription() +"'");

                    rs.close();
                    st.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case "offerings":
                try {
                    Connection db = DriverManager.getConnection(url, username, password);

                    Statement st = db.createStatement();
                    ResultSet rs = st.executeQuery("UPDATE CPR SET CPR = '"+ input +"' WHERE name = '"+ getOfferings() +"'");

                    rs.close();
                    st.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case "inquirySource":
                try {
                    Connection db = DriverManager.getConnection(url, username, password);

                    Statement st = db.createStatement();
                    ResultSet rs = st.executeQuery("UPDATE CPR SET CPR = '"+ word +"' WHERE name = '"+ getInquirySource() +"'");

                    rs.close();
                    st.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case "grantings":
                try {
                    Connection db = DriverManager.getConnection(url, username, password);

                    Statement st = db.createStatement();
                    ResultSet rs = st.executeQuery("UPDATE CPR SET CPR = '"+ word +"' WHERE name = '"+ getGrantings() +"'");

                    rs.close();
                    st.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case "guardianship":
                try {
                    Connection db = DriverManager.getConnection(url, username, password);

                    Statement st = db.createStatement();
                    ResultSet rs = st.executeQuery("UPDATE CPR SET CPR = '"+ word +"' WHERE name = '"+ getGuardianship() +"'");

                    rs.close();
                    st.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case "contactDetails":
                try {
                    Connection db = DriverManager.getConnection(url, username, password);

                    Statement st = db.createStatement();
                    ResultSet rs = st.executeQuery("UPDATE CPR SET CPR = '"+ word +"' WHERE name = '"+ getContactDetails() +"'");

                    rs.close();
                    st.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case "citizenAgreement":
                try {
                    Connection db = DriverManager.getConnection(url, username, password);

                    Statement st = db.createStatement();
                    ResultSet rs = st.executeQuery("UPDATE CPR SET CPR = '"+ word +"' WHERE name = '"+ getCitizenAgreement() +"'");

                    rs.close();
                    st.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case "citizinMunicipality":
                try {
                    Connection db = DriverManager.getConnection(url, username, password);

                    Statement st = db.createStatement();
                    ResultSet rs = st.executeQuery("UPDATE CPR SET CPR = '"+ word +"' WHERE name = '"+ getCitizinMunicipality() +"'");

                    rs.close();
                    st.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case "specialCircumstances":
                try {
                    Connection db = DriverManager.getConnection(url, username, password);

                    Statement st = db.createStatement();
                    ResultSet rs = st.executeQuery("UPDATE CPR SET specialCircumstances = '"+ word +"' WHERE name = '"+ getSpecialCircumstances() +"'");

                    rs.close();
                    st.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;

         }

        }


}
