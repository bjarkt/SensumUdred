package ACQ;

public interface IGranting {
	char PARAGRAPH = '§';

	/**
	 * Get the description of the offer.
	 * @return description
	 */
	String getDescription();

	/**
	 * Get the paragraph number.
	 * @return paragraph number
	 */
	int getParagraph();
}