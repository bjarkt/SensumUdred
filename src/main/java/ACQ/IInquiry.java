package ACQ;

public interface IInquiry extends ITask {
    /**
     * Accessor method for inquiry description.
     * @return inquiry description.
     */
    String getDescription();

    /**
     * Accessor method for inquiry source.
     * @return  inquiry source.
     */
    String getSource();


}
