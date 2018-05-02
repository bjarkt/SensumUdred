package BLL.Inquiry;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;
import BLL.ACQ.IUser;

public interface IInquiry {

    String getCPR();

    public String getName();

    public void setName(String name);

    public String getAddress();

    public void setAddress(String address);

    public String getDescription();

    public void setDescription(String description);

    public String getGender();

    public void setGender(String gender);

    public String getBirthDate();

    public void setBirthDate(String birthDate);

    public String getCivilStatus();

    public void setCivilStatus(String civilStatus);
}
