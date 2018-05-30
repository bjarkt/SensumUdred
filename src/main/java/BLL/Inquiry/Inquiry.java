package BLL.Inquiry;

import ACQ.IInquiry;
import BLL.Task;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSource() {
        return source;
    }
}