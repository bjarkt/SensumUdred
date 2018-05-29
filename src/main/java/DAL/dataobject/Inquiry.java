package DAL.dataobject;

import ACQ.ElucidationState;
import ACQ.IInquiry;

public class Inquiry implements IInquiry {
    private String source;
    private String description;
    private ElucidationState state;

    public Inquiry(String source, String description, ElucidationState state) {
        this.source = source;
        this.description = description;
        this.state = state;
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

    /**
     * {@inheritDoc}
     */
    @Override
    public ElucidationState getState() {
        return state;
    }
}
