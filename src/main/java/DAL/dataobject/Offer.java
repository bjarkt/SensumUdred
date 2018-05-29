package DAL.dataobject;

import ACQ.IOffer;

public class Offer implements IOffer {
	private String description;
	private int paragraph;

	public Offer(String description, int paragraph) {
		this.description = description;
		this.paragraph = paragraph;
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
	public int getParagraph() {
		return paragraph;
	}
}