package BLL.Inquiry;

import BLL.ACQ.IUser;

import java.io.*;
import java.util.*;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Text;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;


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

    private static void saveInquiry()   {
        try {
            Document document = new Document();

            Element theRoot = new Element("Sensumudred");
            document.setRootElement(theRoot);

            Element patient1 = new Element("Patient1");
            patient1.setAttribute("CPR; ", "230390-0585");
            patient1.setAttribute("Name: ", "Bølle Bob");
            patient1.setAttribute("Address:", "Odensevej 20, Slagelse");
            patient1.setAttribute("Gender", "Trans");
            patient1.setAttribute("Birthdate: ", "23.03.1990");
            patient1.setAttribute("Civilstatus :", "Gift");
            patient1.setAttribute("Description: ", "Han har meget store hænder");
            patient1.setAttribute("RegistrationDate: ", "03.05.2017");

            XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
            xmlOutputter.output(document, new FileOutputStream(new File("DATA/inquiry.xml")));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void editInquiry() {
        try {
            Scanner input = new Scanner(System.in);
            Document document = new Document();
            Element patient2 = new Element("Patient2");

            patient2.setAttribute("CPR; ", String.valueOf(input.hasNext()));
            patient2.setAttribute("Name: ", String.valueOf(input.hasNext()));
            patient2.setAttribute("Address:", String.valueOf(input.hasNext()));
            patient2.setAttribute("Gender", String.valueOf(input.hasNext()));
            patient2.setAttribute("Birthdate: ", String.valueOf(input.hasNext()));
            patient2.setAttribute("Civilstatus :", String.valueOf(input.hasNext()));
            patient2.setAttribute("Description: ", String.valueOf(input.hasNext()));
        //    patient2.setAttribute("RegistrationDate: ", String.valueOf(input.hasNext()));

            XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
            xmlOutputter.output(document, new FileOutputStream(new File("DATA/inquiry.xml")));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
