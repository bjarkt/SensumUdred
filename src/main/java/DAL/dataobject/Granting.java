package DAL.dataobject;

import ACQ.IGranting;

public class Granting implements IGranting {
	private String description;

	public Granting(String description) {
		this.description = description;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDescription() {
		return description;
	}
}