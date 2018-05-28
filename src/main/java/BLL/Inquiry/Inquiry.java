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

    private String description;
    private String source;

    public Inquiry(String description, String source) {
        this.description = description;
        this.source = source;
    }

    public Inquiry(IInquiry inquiry) {
        this.description = inquiry.getDescription();
        this.source = inquiry.getSource();
    }

    public Inquiry() {
        this.description = "";
        this.source = "";
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getSource() {
        return source;
    }

}
