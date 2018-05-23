package BLL.case_opening.third_party_information;

/**
 * An IAttachment describes what an attachment should contain within the system.
 * It is connected to a {@link Request}.
 */
public interface IAttachment {
	/**
	 * Returns what type the data is based on the enum {@link AttachmentEnum}.
	 * @return the type of the data
	 */
	AttachmentEnum getType();

	/**
	 * Returns the data in byte format.
	 * Use {@link IAttachment#getType()} to get what format/type the data is.
	 * @return the data in an array of bytes
	 */
	byte[] getData();

	/**
	 * Get the number of this attachment.
	 * @return number
	 */
	long getNumber();
}