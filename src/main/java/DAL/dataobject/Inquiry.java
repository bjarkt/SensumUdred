package DAL.dataobject;

import ACQ.ElucidationTaskState;
import ACQ.IInquiry;

public class Inquiry implements IInquiry {
    private String source;
    private String description;
    private ElucidationTaskState state;

    public Inquiry(String source, String description, ElucidationTaskState state) {
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
    public ElucidationTaskState getState() {
        return state;
    }
}
