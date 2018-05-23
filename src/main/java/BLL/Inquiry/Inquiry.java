package BLL.Inquiry;

import ACQ.IInquiry;
import ACQ.IUser;
import ACQ.Task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class Inquiry extends Task implements IInquiry {

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getSource() {
        return null;
    }
}
